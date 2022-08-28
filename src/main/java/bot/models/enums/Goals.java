package bot.models.enums;

import java.util.Arrays;

public enum Goals {
  JOB(1, "100% польза"),
  FUN_AND_JOB(2, "50% польза, 50% фан"),
  FUN(3, "100% фан");

  private final Integer value;
  private final String description;

  Goals(Integer value, String description) {
    this.value = value;
    this.description = description;
  }

  public static Goals getGoalByDescription(String text) {
    return Arrays.stream(Goals.values())
        .filter(goal -> goal.description.equals(text))
        .findFirst()
        .orElse(FUN_AND_JOB);
  }

  public Integer getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
