package bot;

import bot.config.BotConfig;
import bot.handler.BotHandler;
import bot.handler.PostHandler;
import bot.models.core.InputMessage;
import java.util.Observable;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class DateCoffeeBot extends TelegramLongPollingBot {
  private final Logger logger = LoggerFactory.getLogger(DateCoffeeBot.class);

  @Autowired
  private BotConfig config;
  @Autowired
  public ApplicationContext applicationContext;

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

  @SneakyThrows
  @Override
  public void onUpdateReceived(Update request) {
    if (request.hasMessage()) {
      String text = request.getMessage().getText();
      Long chatId = request.getMessage().getChatId();
      PostHandler postHandler = applicationContext.getBean(PostHandler.class);
      postHandler.addInputMessage(new InputMessage(chatId, text));
    }
  }

  public void sendMessage(SendMessage sendMessage) throws TelegramApiException {
    executeAsync(sendMessage);
  }
}
