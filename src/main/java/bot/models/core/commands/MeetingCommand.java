package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeetingCommand extends ExecutableCommand {

  @Override
  public String getCommandName() {
    return Commands.MEETING.getName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(MeetingCommand.class);
  }

  @Override
  public String execute() {
    return Commands.MEETING.getCommandText();
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return null;
  }
}
