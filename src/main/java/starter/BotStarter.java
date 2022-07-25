package starter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotStarter {
  private static final Logger logger = LoggerFactory.getLogger(BotStarter.class);

  public static void main(String[] args) {
    logger.info("Старт приложения");
    SpringApplication.run(BotStarter.class, args);
  }
}
