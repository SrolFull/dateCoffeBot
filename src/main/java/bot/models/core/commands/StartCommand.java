package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.START.name();
  }

  @Override
  public String getCommandName() {
    return Commands.START.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.START.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(StartCommand.class);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return new MeetingCommand();
  }
}
