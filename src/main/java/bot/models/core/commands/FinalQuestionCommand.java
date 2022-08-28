package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import bot.service.UserDBService;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
@Scope("prototype")
public class FinalQuestionCommand extends ExecutableCommand {

  @Autowired
  UserDBService userDBService;

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
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(
        String.format(Commands.FINAL_QUESTION.getCommandText(), userDBService.getUserInfo(chatId)));
    return Collections.singletonList(sendMessage);
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.GOAL_QUESTION.getCommand();
  }
}
