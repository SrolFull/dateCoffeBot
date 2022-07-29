package bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramService {

  /**
   * Отправка сообщения
   * @param chatId - id чата, куда отправлять сообщение
   * @param text - сообщение
   * @return Обьект SendMessage для отправки
   */

  SendMessage createResponseMessage(Long chatId, String text);
}
