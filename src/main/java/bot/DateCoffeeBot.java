package bot;

import bot.config.BotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import bot.service.impl.TelegramServiceImpl;

@Component
public class DateCoffeeBot extends TelegramLongPollingBot {
  private final Logger logger = LoggerFactory.getLogger(DateCoffeeBot.class);

  private final BotConfig config;
  private final TelegramServiceImpl service;

  public DateCoffeeBot(BotConfig config,
      TelegramServiceImpl service) {
    this.config = config;
    this.service = service;
    logger.info("Бот успешно запущен...");
  }


  @Override
  public String getBotUsername() {
    return config.getName();
  }

  @Override
  public String getBotToken() {
    return config.getToken();
  }

  @Override
  public void onUpdateReceived(Update request) {
    if (request.hasMessage()) {
      String text = request.getMessage().getText();
      Long chatId = request.getMessage().getChatId();
      try {
        execute(service.createResponseMessage(chatId, text));
      } catch (TelegramApiException e) {
        logger.error(e.getMessage());
      }
    }
  }
}
