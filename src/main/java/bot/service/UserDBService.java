package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.db.Users;
import bot.repository.DateCoffeeRepository;
import bot.utility.Utility;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserDBService {
  private final Logger logger = LoggerFactory.getLogger(UserDBService.class);

  private final DateCoffeeRepository repository;
  private Map<Long, ExecutableCommand> userStatus;

  public UserDBService(DateCoffeeRepository repository) {
    this.repository = repository;
  }


  public Users addUser(Users users) {
    return repository.save(users);
  }

  public ExecutableCommand getUserCurrentCommand(Long chatId) {
    return userStatus.get(chatId);
  }

  @EventListener(ApplicationStartedEvent.class)
  public void getAllChatIdAndCurrentCommand() {
    Map<Long, String> rawMap = repository.getAllChatIdAndCurrentCommand();
    if (rawMap != null && !rawMap.isEmpty()) {
     userStatus = Maps.transformValues(rawMap, Utility::convertStringCommandToObjExecutableCommand);
    }
    logger.info("В базе нет ниодного пользователя");
    userStatus = new HashMap<>();
  }
}
