package bot.handler;

import bot.service.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotHandler {
  @Autowired
  private UserDBService userDBService;
  @Autowired
  private PostHandler postHandler;

}
