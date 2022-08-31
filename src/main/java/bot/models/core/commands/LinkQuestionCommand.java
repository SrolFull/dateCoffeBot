package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkQuestionCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.LINK_QUESTION.name();
  }

  @Override
  public String getCommandName() {
    return Commands.LINK_QUESTION.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.LINK_QUESTION.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(LinkQuestionCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return false;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.INTERESTS_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.PLACE_QUESTION.getCommand();
  }
}
