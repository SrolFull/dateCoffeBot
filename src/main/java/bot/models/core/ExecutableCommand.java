package bot.models.core;

import bot.service.CommandService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ExecutableCommand {
 @Autowired
 CommandService commandService;

 public abstract String getCommandName();
 public abstract Logger getLogger();
 public abstract String execute();
 public abstract ExecutableCommand getNextCommand();
}
