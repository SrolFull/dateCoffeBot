package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import bot.models.enums.WritingButtons;
import bot.models.enums.writingBtns.PlaceQuestionButtons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class PlaceQuestionCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.PLACE_QUESTION.name();
  }

  @Override
  public String getCommandName() {
    return Commands.PLACE_QUESTION.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    return Commands.PLACE_QUESTION.getCommandText();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(PlaceQuestionCommand.class);
  }

  @Override
  public WritingButtons[] getWritingBtns() {
    return PlaceQuestionButtons.values();
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.LINK_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.MEETING.getCommand();
  }

  private KeyboardRow addKeyboardRow(String text) {
    KeyboardRow row = new KeyboardRow();
    row.add(text);
    return row;
  }
}
