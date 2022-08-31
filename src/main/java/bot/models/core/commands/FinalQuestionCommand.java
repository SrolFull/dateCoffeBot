package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.CallBackButtons;
import bot.models.enums.Commands;
import bot.models.enums.callbackBtns.FinalQuestionButtons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalQuestionCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.FINAL_QUESTION.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.FINAL_QUESTION.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return String.format("%s\n%s", Commands.FINAL_QUESTION.getCommandText(), commandText);
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(GoalQuestionsCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return true;
  }

  @Override
  public CallBackButtons[] getCallbackBtns() {
    return FinalQuestionButtons.values();
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return new SaveUserCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.GOAL_QUESTION.getCommand();
  }
}
