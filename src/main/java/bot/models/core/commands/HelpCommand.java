package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.HELP.name();
  }

  @Override
  public String getCommandName() {
    return Commands.HELP.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.HELP.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(HelpCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return false;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return null;
  }
}
