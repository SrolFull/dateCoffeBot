package bot.models.core.commands;

import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StartCommand extends ExecutableCommand {
  private Logger logger = getLogger();

  @Override
  public String getCommandName() {
    return Commands.START.getName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(Commands.START.getClazz());
  }

  @Override
  public void execute() {
    logger.info("Выполняем комманду СТАРТ");
  }
}
