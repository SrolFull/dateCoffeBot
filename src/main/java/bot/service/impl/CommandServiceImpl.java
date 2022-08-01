package bot.service.impl;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import bot.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class CommandServiceImpl implements CommandService {
  Logger logger = LoggerFactory.getLogger(CommandServiceImpl.class);

  @Override
  public ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException {
    return Commands.getCommandByName(commandName);
  }

  @Override
  public void executeCommand(SendMessage response, String commandName) {
    try {
      ExecutableCommand command = defineCommand(commandName);
      logger.info(String.format("Старт выполнение комманды: %s", command.getCommandName()));
      String message = command.execute();
      response.setText(message);
      logger.info(String.format("Комманда: %s, выполнена", command.getCommandName()));
    } catch (UndefinedCommandException e) {
      logger.info("Неизвестная комманда: " + e.getName());
      response.setText("Неизвестная комманда: " + e.getName());
    }
  }
}
