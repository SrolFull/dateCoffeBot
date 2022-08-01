package bot.service;

import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramService {

  /**
   * Отправка сообщения
   * @param chatId - id чата, куда отправлять сообщение
   * @param text - сообщение
   * @return Сообщения для отправки в очереди
   */

  List<SendMessage> createResponseMessages(Long chatId, String text);
}
