package bot;


import bot.config.BotConfig;
import bot.service.impl.TelegramServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class BotStarter {
  private static final Logger logger = LoggerFactory.getLogger(BotStarter.class);

  @Autowired
  private DateCoffeeBot dateCoffeeBot;

  public static void main(String[] args) {
    SpringApplication.run(BotStarter.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void runDateCoffeeBot() {
    try {
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
      telegramBotsApi.registerBot(dateCoffeeBot);
      logger.info("Бот успешно запущен");
    } catch (TelegramApiException e) {
      logger.warn("Не удалось запустить бота...");
      logger.warn(e.getMessage());
    }
  }
}
