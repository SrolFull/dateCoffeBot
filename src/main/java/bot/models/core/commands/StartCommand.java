package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends ExecutableCommand {

  @Override
  public String getCommandName() {
    return Commands.START.getName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(StartCommand.class);
  }

  @Override
  public String execute() {
    return Commands.START.getCommandText();
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return new MeetingCommand();
  }
}
