package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MeetingCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.MEETING.name();
  }

  @Override
  public String getCommandName() {
    return Commands.MEETING.getCommandName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(MeetingCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String text) {
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(Commands.MEETING.getCommandText());

    return Collections.singletonList(sendMessage);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return null;
  }


  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.START.getCommand();
  }
}
