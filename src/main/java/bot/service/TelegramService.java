package bot.service;

import java.util.Queue;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramService {

  /**
   * Отправка сообщения
   * @param chatId - id чата, куда отправлять сообщение
   * @param text - сообщение
   * @return Сообщения для отправки в очереди
   */

  Queue<SendMessage> createResponseMessages(Long chatId, String text);
}
