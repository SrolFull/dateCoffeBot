package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.Queue;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandService {
  ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException;
  Queue<SendMessage> executeCommand(Long chatId, String text);
}
