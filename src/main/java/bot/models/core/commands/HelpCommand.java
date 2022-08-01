package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class HelpCommand extends ExecutableCommand {

  @Override
  public String getCommandName() {
    return Commands.HELP.getName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(HelpCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String text) {
    List<SendMessage> list = new ArrayList<>();

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
