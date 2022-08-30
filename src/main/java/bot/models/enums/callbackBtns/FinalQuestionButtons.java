package bot.models.enums.callbackBtns;

import bot.models.enums.CallBackButtons;

public enum FinalQuestionButtons implements CallBackButtons {
  SAVE("Сохранить", "save"),
  CHANGE("Изменить", "change");

  private final String description;
  private final String action;

  FinalQuestionButtons(String description, String action) {
    this.description = description;
    this.action = action;
  }

  public String getDescription() {
    return description;
  }

  public String getAction() {
    return action;
  }
}
