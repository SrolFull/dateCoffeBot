package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandService {
  ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException;
  List<SendMessage> executeCommand(InputMessage inputMessage);
  List<SendMessage> executeCommand(InputMessage inputMessage, ExecutableCommand command);

  void saveAnswer(Commands command, InputMessage inputMessage);
}
