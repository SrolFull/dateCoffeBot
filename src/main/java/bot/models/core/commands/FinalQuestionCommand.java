package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
public class FinalQuestionCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
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
  public Logger getLogger() {
    return LoggerFactory.getLogger(GoalQuestionsCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String commandText) {
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(
        String.format("%s\n%s",Commands.FINAL_QUESTION.getCommandText(), commandText));
    return Collections.singletonList(sendMessage);
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.GOAL_QUESTION.getCommand();
  }
}
