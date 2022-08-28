package bot.handler;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.db.Users;
import bot.models.enums.Commands;
import bot.service.UserDBService;
import bot.service.impl.CommandServiceImpl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class BotHandler {
  @Autowired
  private UserDBService userDBService;
  @Autowired
  private CommandServiceImpl commandService;
  private final Logger logger = LoggerFactory.getLogger(BotHandler.class);


  //Ждём ли ответа от пользователя на вопрос
  public static Map<Long, Boolean> isWaitingQuestionAnswer = new HashMap<>();

  public List<SendMessage> handleMessage(Message message) {
    InputMessage inputMessage = new InputMessage(message.getChatId(), message.getText());
    Boolean isWaitingAnswer = isWaitingQuestionAnswer.getOrDefault(inputMessage.getChatId(), false);
    if (isWaitingAnswer != null && isWaitingAnswer) {
      return handleUserAnswer(inputMessage);
    } else {
      return handleUserCommand(inputMessage);
    }
  }

  @SneakyThrows
  private List<SendMessage> handleUserAnswer(InputMessage inputMessage) {
    ExecutableCommand lastCommand = userDBService.getUserLastCommand(inputMessage.getChatId());
    Commands command = Commands.getCommandByCommandName(lastCommand.getCommandName());
    commandService.saveAnswer(command, inputMessage);
    ExecutableCommand nextCommand = lastCommand.getNextCommand();
    if (Commands.FINAL_QUESTION.getName().equals(nextCommand.getName())) {
      inputMessage.setText(userDBService.getUserInfo(inputMessage.getChatId()));
    }
    return commandService.executeCommand(inputMessage, nextCommand);
  }

  private List<SendMessage> handleUserCommand(InputMessage inputMessage) {
    List<SendMessage> response = new LinkedList<>();
      try {
        ExecutableCommand executableCommand = commandService.defineCommand(inputMessage.getText());
        if (Commands.START.getName().equals(executableCommand.getName())) {
          userDBService.addNewUser(inputMessage.getChatId());
        }
        response.addAll(commandService.executeCommand(inputMessage));
        if (!isWaitingQuestionAnswer.get(inputMessage.getChatId()) && executableCommand.getNextCommand() != null) {
          inputMessage.setText(executableCommand.getNextCommand().getCommandName());
          response.addAll(handleUserCommand(inputMessage));
        }
      } catch (UndefinedCommandException exception) {
        logger.debug("Не удалось определить комманду");
      }
      return response;
  }
}
