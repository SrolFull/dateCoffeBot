package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterestsQuestionCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.INTERESTS_QUESTION.name();
  }

  @Override
  public String getCommandName() {
    return Commands.INTERESTS_QUESTION.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.INTERESTS_QUESTION.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(InterestsQuestionCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return false;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.WHATS_YOUR_JOB_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.LINK_QUESTION.getCommand();
  }
}
