package bot.models.enums;

import bot.models.core.ExecutableCommand;
import bot.models.core.commands.HelpCommand;
import bot.models.core.commands.MeetingCommand;
import bot.models.core.commands.StartCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.Arrays;

public enum Commands {
  START("Старт общения","/start", new StartCommand(), Messages.START_MESSAGE.getMessage()),
  HELP("Справочник", "/help", new HelpCommand(), Messages.QUESTION_HELP.getMessage()),
  MEETING("Знакомство", "/meeting", new MeetingCommand(), Messages.QUESTION_ONE.getMessage());

  private final String description;
  private final String commandName;
  private final ExecutableCommand command;
  private final String commandText;

  public static ExecutableCommand getExecutableCommandByName(String name) throws UndefinedCommandException {
    Commands command = getCommandByCommandName(name);
    return command.getCommand();
  }

  public static Commands getCommandByCommandName(String name) throws UndefinedCommandException {
    Commands command =  Arrays.stream(Commands.values())
        .filter(v -> v.getCommandName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
    if (command == null) {
      throw new UndefinedCommandException(name);
    }
    return command;
  }

  Commands(String description, String commandName, ExecutableCommand command, String commandText) {
    this.description = description;
    this.commandName = commandName;
    this.command = command;
    this.commandText = commandText;
  }


  public String getName() {
    return this.name();
  }

  public ExecutableCommand getCommand() {
    return command;
  }

  public String getCommandName() {
    return commandName;
  }

  public String getCommandText() {
    return commandText;
  }
}
