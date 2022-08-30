package bot.models.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
public class InputMessage {
  private Long chatId;
  private String text;
  private String callBackId;

  public InputMessage(Long chatId, String text) {
    this.chatId = chatId;
    this.text = text;
  }
}
