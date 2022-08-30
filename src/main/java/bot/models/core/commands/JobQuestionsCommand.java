package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobQuestionsCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.WHATS_YOUR_JOB_QUESTION.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.WHATS_YOUR_JOB_QUESTION.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.WHATS_YOUR_JOB_QUESTION.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(JobQuestionsCommand.class);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.GOAL_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.INTERESTS_QUESTION.getCommand();
  }
}
