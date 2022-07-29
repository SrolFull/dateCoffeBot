package bot.models.core;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;

@Aspect
public class ExecutableCommandAspect {
  private final Logger logger;
  private final String name;

  public ExecutableCommandAspect(ExecutableCommand command) {
    logger = command.getLogger();
    name = command.getCommandName();
  }

  public void logBeforeExecute() {
    logger.info(String.format("Старт выполнение комманды: %s", name));
  }

  public void logAfterExecute() {
    logger.info(String.format("Комманда: %s, выполнена", name));
  }
}
