package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.CallBackButtons;
import bot.models.enums.Commands;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

public class SaveUserCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.SAVE_USER.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.SAVE_USER.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return null;
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(SaveUserCommand.class);
  }

  @Override
  public CallBackButtons[] getCallbackBtns() {
    return super.getCallbackBtns();
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return super.getNextCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return super.getPreviousCommand();
  }

  @Override
  public List<AnswerCallbackQuery> execute(Long chatId, String text, String id) {
    return super.execute(chatId, text, id);
  }
}
