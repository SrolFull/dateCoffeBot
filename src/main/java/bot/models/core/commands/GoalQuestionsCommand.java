package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import bot.models.enums.WritingButtons;
import bot.models.enums.writingBtns.GoalQuestionButtons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoalQuestionsCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.GOAL_QUESTION.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.GOAL_QUESTION.getCommandName();
  }

  @Override
  public WritingButtons[] getWritingBtns() {
    return GoalQuestionButtons.values();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.GOAL_QUESTION.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(GoalQuestionsCommand.class);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.FINAL_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.WHATS_YOUR_JOB_QUESTION.getCommand();
  }
}
