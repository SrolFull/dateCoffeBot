package bot.models.enums;

import bot.models.core.ExecutableCommand;
import bot.models.core.commands.HelpCommand;
import bot.models.core.commands.StartCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.Arrays;

public enum Commands {
  START("Старт общения","/start", new StartCommand()),
  HELP("Спрачоник", "/help", new HelpCommand());

  private final String name;
  private final String commandName;
  private final ExecutableCommand command;

  public static ExecutableCommand getCommandByName(String name) throws UndefinedCommandException {
    Commands command =  Arrays.stream(Commands.values())
        .filter(v -> v.getCommandName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
    if (command == null) {
      throw new UndefinedCommandException(name);
    }
    return command.getCommand();
  }

  Commands(String name, String commandName, ExecutableCommand command) {
    this.name = name;
    this.commandName = commandName;
    this.command = command;
  }


  public String getName() {
    return name;
  }

  public ExecutableCommand getCommand() {
    return command;
  }

  public String getCommandName() {
    return commandName;
  }
}
