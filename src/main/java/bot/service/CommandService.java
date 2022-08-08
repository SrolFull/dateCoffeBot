package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;

public interface CommandService {
  ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException;
  void executeCommand(InputMessage inputMessage);
}
