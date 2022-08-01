package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandService {
  ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException;
  List<SendMessage> executeCommand(Long chatId, String text);
}
