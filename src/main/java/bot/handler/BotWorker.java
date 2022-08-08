package bot.handler;

import bot.DateCoffeeBot;
import bot.models.core.InputMessage;
import bot.models.enums.MessageType;
import bot.service.impl.CommandServiceImpl;
import java.util.Observable;
import java.util.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotWorker implements Observer {

  @Autowired
  private PostHandler postHandler;

  @Autowired
  private ApplicationContext context;

  @Autowired
  private CommandServiceImpl commandService;

  @Override
  public void update(Observable o, Object arg) {
    MessageType messageType = (MessageType) arg;
    if (MessageType.INPUT.equals(messageType)) {
      InputMessage inputMessage = postHandler.getInputMessage();
      new Thread(() ->
          commandService.executeCommand(inputMessage)).start();
    } else {
      SendMessage sendMessage = postHandler.getOutputMessage();
      new Thread(() -> {
        DateCoffeeBot coffeeBot = context.getBean(DateCoffeeBot.class);
        try {
          coffeeBot.execute(sendMessage);
        } catch (TelegramApiException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
