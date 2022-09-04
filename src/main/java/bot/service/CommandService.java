package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.InputMessage;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface CommandService {
  ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException;
  List<SendMessage> executeCommand(InputMessage inputMessage);
  List<SendMessage> executeCommand(InputMessage inputMessage, ExecutableCommand command);
  List<EditMessageText> executeCommand(InputMessage inputMessage, ExecutableCommand command, Integer id);


  void saveAnswer(String commandName, InputMessage inputMessage);
}
