package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeetingCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.MEETING.name();
  }

  @Override
  public String getCommandName() {
    return Commands.MEETING.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.MEETING.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(MeetingCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return false;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.PLACE_QUESTION.getCommand();
  }


  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.START.getCommand();
  }


}
