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
}
