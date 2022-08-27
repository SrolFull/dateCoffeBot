package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class HelpCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return false;
  }

  @Override
  public String getName() {
    return Commands.HELP.name();
  }

  @Override
  public String getCommandName() {
    return Commands.HELP.getCommandName();
  }


  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(HelpCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String text) {
    List<SendMessage> list = new ArrayList<>();
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());
    SendMessage startMessage = new SendMessage();
    startMessage.setChatId(chatId);
    startMessage.setText(Commands.START.getCommandText());

    list.add(startMessage);

    return list;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return null;
  }
}
