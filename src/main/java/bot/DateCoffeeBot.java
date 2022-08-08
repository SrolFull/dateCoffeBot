package bot;

import bot.config.BotConfig;
import bot.handler.PostHandler;
import bot.models.core.InputMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class DateCoffeeBot extends TelegramLongPollingBot {
  private final Logger logger = LoggerFactory.getLogger(DateCoffeeBot.class);

  private final BotConfig config;
  private final PostHandler postHandler;

  public DateCoffeeBot(BotConfig config,
      PostHandler postHandler) {
    this.config = config;
    this.postHandler = postHandler;
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
      postHandler.addInputMessage(new InputMessage(chatId, text));
    }
  }

  public void sendMessage(SendMessage sendMessage) throws TelegramApiException {
    executeAsync(sendMessage);
  }
}
