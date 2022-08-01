package bot.models.core;

import bot.service.CommandService;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class ExecutableCommand {
 @Autowired
 CommandService commandService;

 public abstract String getCommandName();
 public abstract Logger getLogger();
 public abstract List<SendMessage> execute(Long chatId, String commandText);
 public abstract ExecutableCommand getNextCommand();
}
