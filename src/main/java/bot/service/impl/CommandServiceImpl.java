package bot.service.impl;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import bot.service.CommandService;
import java.util.PriorityQueue;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class CommandServiceImpl implements CommandService {
  Logger logger = LoggerFactory.getLogger(CommandServiceImpl.class);

  @Override
  public ExecutableCommand defineCommand(String commandName) throws UndefinedCommandException {
    return Commands.getCommandByName(commandName);
  }

  @Override
  public Queue<SendMessage> executeCommand(Long chatId, String text) {
    Queue<SendMessage> outputMessages = new PriorityQueue<>();
    try {
      ExecutableCommand command = defineCommand(text);
      logger.info(String.format("Старт выполнение комманды: %s", command.getCommandName()));
      outputMessages = command.execute(chatId, text);
      logger.info(String.format("Комманда: %s, выполнена", command.getCommandName()));
    } catch (UndefinedCommandException e) {
      logger.info("Неизвестная комманда: " + e.getName());
      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chatId);
      sendMessage.setText("Неизвестная комманда: " + e.getName());
      outputMessages.add(sendMessage);
    }
    return outputMessages;
  }
}
