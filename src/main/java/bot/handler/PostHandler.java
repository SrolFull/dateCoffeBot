package bot.handler;

import bot.models.core.InputMessage;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.SynchronousQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PostHandler extends Observable {
  private SynchronousQueue<InputMessage> inputMessages = new SynchronousQueue<>();
  private SynchronousQueue<SendMessage> outputMessages = new SynchronousQueue<>();

  @Autowired
  public PostHandler()  {
    addObserver(new BotWorker());
  }

  public InputMessage getInputMessage() {
    return inputMessages.poll();
  }

  public SendMessage getOutputMessage() {
    return outputMessages.poll();
  }

  public void addInputMessage(InputMessage message) {
    notifyObservers();
    inputMessages.add(message);
  }

  public void addOutputMessage(List<SendMessage> message) {
    notifyObservers();
    outputMessages.addAll(message);
  }

}
