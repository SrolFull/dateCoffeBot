package bot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import bot.service.TelegramService;

@Service
public class TelegramServiceImpl implements TelegramService {
  @Autowired
  private CommandServiceImpl commandServiceImpl;

  @Override
  public SendMessage createResponseMessage(Long chatId, String text) {
    SendMessage response = new SendMessage();
    response.enableMarkdown(true);
    response.setChatId(chatId);
    commandServiceImpl.executeCommand(response, text);
    return response;
  }
}
