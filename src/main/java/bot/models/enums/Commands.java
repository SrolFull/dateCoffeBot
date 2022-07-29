package bot.models.enums;

import bot.models.core.commands.StartCommand;

public enum Commands {
  START("Старт общения", StartCommand.class);

  private final String name;
  private final Class clazz;

  Commands(String name, Class clazz) {
    this.name = name;
    this.clazz = clazz;
  }


  public String getName() {
    return name;
  }

  public Class getClazz() {
    return clazz;
  }
}
