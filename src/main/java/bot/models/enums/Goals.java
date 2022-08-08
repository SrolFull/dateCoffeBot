package bot.models.enums;

public enum Goals {
  JOB(1),
  FUN_AND_JOB(2),
  FUN(3);

  private final Integer value;

  Goals(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
