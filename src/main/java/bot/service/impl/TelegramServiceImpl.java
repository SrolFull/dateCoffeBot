package bot.service.impl;

import bot.models.core.ExecutableCommand;
import bot.service.UserDBService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import bot.service.TelegramService;

@Service
public class TelegramServiceImpl implements TelegramService {
  @Autowired
  private CommandServiceImpl commandServiceImpl;

  @Autowired
  private UserDBService userDBService;
  @Override
  public List<SendMessage> createResponseMessages(Long chatId, String text) {
    return commandServiceImpl.executeCommand(chatId, text);
  }
}
