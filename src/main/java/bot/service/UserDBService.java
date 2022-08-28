package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.db.Users;
import bot.repository.DateCoffeeRepository;
import bot.utility.Utility;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class UserDBService {
  @Autowired
  private UserService userService;
  private final Logger logger = LoggerFactory.getLogger(UserDBService.class);

  private final DateCoffeeRepository repository;
  private Map<Long, ExecutableCommand> userLastCommandMap = new HashMap<>();


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
  }

  @Transactional
  public void updateFirstAndLastName(Long chatId, String firstName, String lastName) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateFirstAndLastName(user1, firstName, lastName);
          repository.save(newUser);
        });
  }

  public void updateUserPlace(Long chatId, String place) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateUserPlace(user1, place);
          repository.save(newUser);
        });
  }

  public void updateUserLink(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateUserLink(user1, text);
          repository.save(newUser);
        });
  }

  public void updateUserInterests(Long chatId, List<String> params) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateUserInterests(user1, params);
          repository.save(newUser);
        });
  }

  public void updateUserJob(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateUserJob(user1, text);
          repository.save(newUser);
        });
  }

  public void updateUserGoal(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          Users newUser = userService.updateUserGoal(user1, text);
          repository.save(newUser);
        });
  }

  public String getUserInfo(Long chatId) {
    Users user = repository.findByChatId(chatId).orElse(null);
    if (user == null) {
      return "Не удалось получить информацию о пользователе";
    } else {
      return user.toString();
    }
  }
}
