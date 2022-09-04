package bot.models.enums;

public enum Emoji {
  WHITE_CHECK_MARK(":white_check_mark:");

  private final String code;

  Emoji(String unicode) {
    this.code = unicode;
  }

  public String getCode() {
    return code;
  }
}
