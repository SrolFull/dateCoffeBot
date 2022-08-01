package bot.models.core.exceptions;

public class UndefinedCommandException extends Exception {
  private final String name;

  public UndefinedCommandException(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
