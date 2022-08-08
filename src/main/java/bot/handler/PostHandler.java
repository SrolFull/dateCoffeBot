package bot.handler;

import bot.models.core.InputMessage;
import bot.models.enums.MessageType;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PostHandler extends Observable {
  private Queue<InputMessage> inputMessages = new LinkedList<>();
  private Queue<SendMessage> outputMessages = new LinkedList<>();

  @Autowired
  public ApplicationContext context;

  public InputMessage getInputMessage() {
    return inputMessages.poll();
  }

  public SendMessage getOutputMessage() {
    return outputMessages.poll();
  }

  public void addInputMessage(InputMessage message) {
    inputMessages.add(message);
    setChanged();
    notifyObservers(MessageType.INPUT);
  }

  public void addOutputMessage(List<SendMessage> messages) {
    outputMessages.addAll(messages);
    setChanged();
    notifyObservers(MessageType.OUTPUT);
  }

  @EventListener(ApplicationStartedEvent.class)
  public void addObservers() {
    addObserver(new BotWorker(this, context));
  }
}
