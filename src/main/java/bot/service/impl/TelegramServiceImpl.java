package bot.service.impl;

import bot.models.core.commands.StartCommand;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import bot.service.TelegramService;

@Service
public class TelegramServiceImpl implements TelegramService {

  @Autowired
  private StartCommand startCommand;

  @Override
  public SendMessage createResponseMessage(Long chatId, String text) {
    SendMessage response = new SendMessage();
    response.enableMarkdown(true);
    response.setChatId(chatId);
    response.setText(text);
    startCommand.execute();
    return response;
  }
}
