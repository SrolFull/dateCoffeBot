package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.db.Users;
import bot.repository.DateCoffeeRepository;
import bot.utility.Utility;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
@Scope("singleton")
public class UserDBService {
  @Autowired
  private UserService userService;
  private final Logger logger = LoggerFactory.getLogger(UserDBService.class);

  private final DateCoffeeRepository repository;
  private Map<Long, ExecutableCommand> userLastCommandMap;


  public UserDBService(DateCoffeeRepository repository) {
    this.repository = repository;
  }


  public Users addUser(Users users) {
    return repository.save(users);
  }

  public ExecutableCommand getUserLastCommand(Long chatId) {
    return userLastCommandMap.get(chatId);
  }

  public void updateUserLastCommand(Long chatId, ExecutableCommand command) {
    userLastCommandMap.put(chatId, command);
  }

  @EventListener(ApplicationStartedEvent.class)
  public void fillUserLastCommandMap() {
    Map<Long, String> rawMap = repository.getAllChatIdAndCurrentCommand();
    if (rawMap != null && !rawMap.isEmpty()) {
     userLastCommandMap = Maps.transformValues(rawMap, Utility::convertStringCommandToObjExecutableCommand);
    }
    logger.info("В базе нет ниодного пользователя");
    userLastCommandMap = new HashMap<>();
  }

  @Transactional
  public void updateFirstAndLastName(Long chatId, String firstName, String lastName) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateFirstAndLastName(user1, firstName, lastName);
          repository.save(newUser);
        });
  }
}
