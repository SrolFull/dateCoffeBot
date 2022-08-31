package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.CallBackButtons;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public Boolean needKeyboard() {
    return true;
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
}
