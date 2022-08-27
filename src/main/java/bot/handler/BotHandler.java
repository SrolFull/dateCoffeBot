package bot.handler;

import bot.DateCoffeeBot;
import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import bot.service.UserDBService;
import bot.service.impl.CommandServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotHandler {
  @Autowired
  private UserDBService userDBService;
  @Autowired
  private CommandServiceImpl commandService;
  @Autowired
  private DateCoffeeBot dateCoffeeBot;

  private final Logger logger = LoggerFactory.getLogger(BotHandler.class);


  //Ждём ли ответа от пользователя на вопрос
  public static Map<Long, Boolean> isWaitingQuestionAnswer = new HashMap<>();

  public void handleMessage(Message message) {
    InputMessage inputMessage = new InputMessage(message.getChatId(), message.getText());
    Boolean isWaitingAnswer = isWaitingQuestionAnswer.getOrDefault(inputMessage.getChatId(), false);
    if (isWaitingAnswer != null && isWaitingAnswer) {
      handleUserAnswer(inputMessage);
    } else {
      handleUserCommand(inputMessage);
    }
  }

  @SneakyThrows
  private void handleUserAnswer(InputMessage inputMessage) {
    ExecutableCommand lastCommand = userDBService.getUserLastCommand(inputMessage.getChatId());
    Commands command = Commands.getCommandByName(lastCommand.getCommandName());
    commandService.saveAnswer(command, inputMessage);
    ExecutableCommand nextCommand = lastCommand.getNextCommand();
    List<SendMessage> response = commandService.executeCommand(inputMessage, nextCommand);
    sendMessages(response);
  }

  private void handleUserCommand(InputMessage inputMessage) {
      try {
        ExecutableCommand executableCommand = commandService.defineCommand(inputMessage.getText());
        userDBService.updateUserLastCommand(inputMessage.getChatId(), executableCommand);
      } catch (UndefinedCommandException exception) {
        logger.debug("Не удалось определить комманду");
      }
      List<SendMessage> response = commandService.executeCommand(inputMessage);
      sendMessages(response);
  }

  private void sendMessages(List<SendMessage> sendMessages) {
    sendMessages.forEach(message -> {
      try {
        dateCoffeeBot.execute(message);
      } catch (TelegramApiException exception) {
        logger.warn("Не удалось отправить сообщение," + exception.getMessage());
      }
    });
  }
}
