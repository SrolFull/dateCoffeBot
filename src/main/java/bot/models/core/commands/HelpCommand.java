package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpCommand extends ExecutableCommand {

  @Override
  public String getCommandName() {
    return Commands.HELP.getName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(HelpCommand.class);
  }

  @Override
  public String execute() {
    return String.format("Выполняем комманду: %s", getCommandName());
  }
}
