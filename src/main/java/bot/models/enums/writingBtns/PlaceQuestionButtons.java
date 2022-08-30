package bot.models.enums.writingBtns;

import bot.models.enums.WritingButtons;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PlaceQuestionButtons implements WritingButtons {
  ENGELSA("Энгельса 36"),
  GORKOGO("Горького 67"),
  GOGOLYA("Гоголя 44"),
  KUYBISHEVA("Куйбышева 67");

  private final String text;

  PlaceQuestionButtons(String text) {
    this.text = text;
  }

  @Override
  public List<String> getTexts() {
    return Arrays.stream(values())
        .map(PlaceQuestionButtons::getText)
        .collect(Collectors.toList());
  }

  @Override
  public String getText() {
    return text;
  }
}
