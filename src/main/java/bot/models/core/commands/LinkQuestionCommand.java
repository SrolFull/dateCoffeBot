package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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
  public Logger getLogger() {
    return LoggerFactory.getLogger(LinkQuestionCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String commandText) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(Commands.LINK_QUESTION.getCommandText());
    return Collections.singletonList(sendMessage);
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
