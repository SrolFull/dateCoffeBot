package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
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
  public Queue<SendMessage> execute(Long chatId, String text) {
    Queue<SendMessage> queue = new PriorityQueue<>();

    SendMessage startMessage = new SendMessage();
    startMessage.setChatId(chatId);
    startMessage.setText(Commands.START.getCommandText());

    queue.add(startMessage);

    return queue;
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return null;
  }
}
