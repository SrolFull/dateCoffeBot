package bot.service.impl;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import bot.service.CommandService;
import bot.service.UserDBService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class CommandServiceImpl implements CommandService {
  Logger logger = LoggerFactory.getLogger(CommandServiceImpl.class);

  @Autowired
  private UserDBService userDBService;

  @Override
  public ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException {
    return Commands.getExecutableCommandByName(commandName);
  }

  @Override
  public List<SendMessage> executeCommand(InputMessage inputMessage) {
    List<SendMessage> outputMessages = new ArrayList<>();
    try {
      ExecutableCommand command = defineCommand(inputMessage.getText());
      logger.info(String.format("Старт выполнение комманды: %s", command.getName()));
      outputMessages = command.execute(inputMessage.getChatId(), inputMessage.getText());
      logger.info(String.format("Комманда: %s, выполнена", command.getName()));
      userDBService.updateUserLastCommand(inputMessage.getChatId(), command);
    } catch (UndefinedCommandException e) {
      logger.info("Неизвестная комманда: " + e.getName());
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(inputMessage.getChatId());
      sendMessage.setText("Неизвестная комманда: " + e.getName());
      outputMessages.add(sendMessage);
    }
    return outputMessages;
  }

  @Override
  public List<SendMessage> executeCommand(InputMessage inputMessage, ExecutableCommand command) {
    logger.debug(String.format("Старт выполнение комманды: %s", command.getName()));
    List<SendMessage> outputMessages = command.execute(inputMessage.getChatId(), inputMessage.getText());
    logger.debug(String.format("Комманда: %s, выполнена", command.getName()));
    userDBService.updateUserLastCommand(inputMessage.getChatId(), command);
    return outputMessages;
  }

  @Override
  public void saveAnswer(Commands command, InputMessage inputMessage) {
    String commandName = command.getCommandName();
    if (Commands.MEETING.getName().equals(commandName)) {
      String[] params = inputMessage.getText().split(" ");
      userDBService.updateFirstAndLastName(inputMessage.getChatId(),params[0], params[1]);
    } else if (Commands.PLACE_QUESTION.getName().equals(commandName)) {
      userDBService.updateUserPlace(inputMessage.getChatId(), inputMessage.getText());
    } else if (Commands.LINK_QUESTION.getName().equals(commandName)) {
      userDBService.updateUserLink(inputMessage.getChatId(), inputMessage.getText());
    } else if (Commands.INTERESTS_QUESTION.getName().equals(commandName)) {
      String[] params = inputMessage.getText().split(",");
      userDBService.updateUserInterests(inputMessage.getChatId(), Arrays.asList(params));
    } else if (Commands.WHATS_YOUR_JOB_QUESTION.getName().equals(commandName)) {
      userDBService.updateUserJob(inputMessage.getChatId(), inputMessage.getText());
    }else if (Commands.GOAL_QUESTION.getName().equals(commandName)) {
      userDBService.updateUserGoal(inputMessage.getChatId(), inputMessage.getText());
    }
  }
}
