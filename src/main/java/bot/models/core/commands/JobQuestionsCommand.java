package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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
  public Logger getLogger() {
    return LoggerFactory.getLogger(JobQuestionsCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String commandText) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setText(Commands.WHATS_YOUR_JOB_QUESTION.getCommandText());
    sendMessage.setChatId(chatId);
    return Collections.singletonList(sendMessage);
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
