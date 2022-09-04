package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import bot.models.enums.Emoji;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveUserCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.SAVE_USER.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.SAVE_USER.getCommandName();
  }

  @Override
  public String getMessageText(String commandText) {
    String successSave = "Данные успешно сохранены";
    return EmojiParser.parseToUnicode(
        String.format("%s \n\n%s %s", commandText, successSave, Emoji.WHITE_CHECK_MARK.getCode())
    );
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.FINAL_QUESTION.getCommand();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(SaveUserCommand.class);
  }

  @Override
  public Boolean needKeyboard() {
    return false;
  }
}
