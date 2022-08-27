package bot;

import bot.config.BotConfig;
import bot.handler.BotHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Scope("singleton")
public class DateCoffeeBot extends TelegramLongPollingBot {
  private final Logger logger = LoggerFactory.getLogger(DateCoffeeBot.class);

  @Autowired
  private BotConfig config;
  @Autowired
  private BotHandler botHandler;

  public DateCoffeeBot(BotConfig config) {
    this.config = config;
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
       botHandler.handleMessage(request.getMessage())
           .forEach(message -> {
             try {
               execute(message);
             } catch (TelegramApiException e) {
               e.printStackTrace();
             }
           });
    }
  }
}
