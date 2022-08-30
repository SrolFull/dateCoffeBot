package bot.models.enums.writingBtns;

import bot.models.enums.WritingButtons;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GoalQuestionButtons implements WritingButtons {
  JOB("100% польза"),
  FUN_AND_JOB("50% польза, 50% фан"),
  FUN("100% фан");

  private final String text;

  GoalQuestionButtons(String text) {
    this.text = text;
  }

  @Override
  public List<String> getTexts() {
    return Arrays.stream(values())
        .map(GoalQuestionButtons::getText)
        .collect(Collectors.toList());
  }

  @Override
  public String getText() {
    return text;
  }
}
