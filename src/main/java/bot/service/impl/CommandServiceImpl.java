package bot.service.impl;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import bot.service.CommandService;
import bot.service.UserDBService;
import bot.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

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
  @SneakyThrows
  public List<EditMessageText> executeCommand(InputMessage inputMessage, ExecutableCommand command, Integer id) {
    logger.debug(String.format("Старт выполнение комманды: %s", command.getName()));
    List<EditMessageText> outputMessages = command.execute(inputMessage.getChatId(), inputMessage.getText(), id);
    saveAnswer(command.getName(), inputMessage);
    logger.debug(String.format("Комманда: %s, выполнена", command.getName()));
    userDBService.updateUserLastCommand(inputMessage.getChatId(), command);
    return outputMessages;
  }

  @Override
  public void saveAnswer(String commandName, InputMessage inputMessage) {
    if (Commands.MEETING.name().equals(commandName)) {
      String[] params = inputMessage.getText().split(" ");
      userDBService.updateFirstAndLastName(inputMessage.getChatId(),params[0], params[1]);
    } else if (Commands.PLACE_QUESTION.name().equals(commandName)) {
      userDBService.updateUserPlace(inputMessage.getChatId(), inputMessage.getText());
    } else if (Commands.LINK_QUESTION.name().equals(commandName)) {
      userDBService.updateUserLink(inputMessage.getChatId(), inputMessage.getText());
    } else if (Commands.INTERESTS_QUESTION.name().equals(commandName)) {
      userDBService.updateUserInterests(inputMessage.getChatId(), Utility.convertStringToList(inputMessage.getText()));
    } else if (Commands.WHATS_YOUR_JOB_QUESTION.name().equals(commandName)) {
      userDBService.updateUserJob(inputMessage.getChatId(), inputMessage.getText());
    }else if (Commands.GOAL_QUESTION.name().equals(commandName)) {
      userDBService.updateUserGoal(inputMessage.getChatId(), inputMessage.getText());
    }
  }
}
