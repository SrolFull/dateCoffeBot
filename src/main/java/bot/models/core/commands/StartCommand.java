package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.START.name();
  }

  @Override
  public String getCommandName() {
    return Commands.START.getCommandName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(StartCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String text) {
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(Commands.START.getCommandText());

    return Collections.singletonList(sendMessage);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return new MeetingCommand();
  }
}
