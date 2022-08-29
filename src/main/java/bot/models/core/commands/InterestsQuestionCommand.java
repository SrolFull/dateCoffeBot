package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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
  public Logger getLogger() {
    return LoggerFactory.getLogger(InterestsQuestionCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String commandText) {
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(Commands.INTERESTS_QUESTION.getCommandText());
    return Collections.singletonList(sendMessage);
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
