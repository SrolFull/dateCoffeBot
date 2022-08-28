package bot.models.core;

import java.util.List;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public abstract class ExecutableCommand {
 public abstract Boolean isNeedWaitingResponse();
 public abstract String getName();
 public abstract String getCommandName();
 public abstract Logger getLogger();
 public abstract List<SendMessage> execute(Long chatId, String commandText);
 public ExecutableCommand getNextCommand() {
  return null;
 }
 public ExecutableCommand getPreviousCommand(){
  return null;
 }
 public ReplyKeyboard getKeyboardMarkup() {
  return null;
 }
}
