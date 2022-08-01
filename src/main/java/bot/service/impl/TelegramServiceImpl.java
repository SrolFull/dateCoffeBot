package bot.service.impl;

import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import bot.service.TelegramService;

@Service
public class TelegramServiceImpl implements TelegramService {
  @Autowired
  private CommandServiceImpl commandServiceImpl;

  @Override
  public Queue<SendMessage> createResponseMessages(Long chatId, String text) {
    return commandServiceImpl.executeCommand(chatId, text);
  }
}
