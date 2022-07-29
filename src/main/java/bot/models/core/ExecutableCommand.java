package bot.models.core;

import org.slf4j.Logger;
public abstract class ExecutableCommand {
 public abstract String getCommandName();
  public abstract Logger getLogger();
  public abstract void execute();

}
