package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.db.IUserCommands;
import bot.models.db.Users;
import bot.repository.DateCoffeeRepository;
import bot.utility.Utility;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class UserDBService {
  private final Logger logger = LoggerFactory.getLogger(UserDBService.class);

  private final DateCoffeeRepository repository;
  private Map<Long, ExecutableCommand> userLastCommandMap = new HashMap<>();


  public UserDBService(DateCoffeeRepository repository) {
    this.repository = repository;
  }

  private static boolean checkNull(IUserCommands command) {
    return command.getId() != null && command.getCommand() != null;
  }

  @Transactional
  public void addNewUser(Long chatId) {
    repository.save(new Users(chatId));
  }

  public ExecutableCommand getUserLastCommand(Long chatId) {
    return userLastCommandMap.get(chatId);
  }

  @Transactional
  public void updateUserLastCommand(Long chatId, ExecutableCommand command) {
    logger.debug(String.format("Обновление последнего шага: chatId = %s, комманда = %s",
        chatId, command));
    if (command != null) {
      updateUserCurrentCommand(chatId, command.getName());
      userLastCommandMap.put(chatId, command);
    }
  }

  @EventListener(ApplicationStartedEvent.class)
  public void fillUserLastCommandMap() {
    Map<Long, String> rawMap;
    List<IUserCommands> iUserCommands = repository.getAllChatIdAndCurrentCommand();
    rawMap = iUserCommands.stream()
        .filter(UserDBService::checkNull)
        .collect(Collectors.toMap(IUserCommands::getId, IUserCommands::getCommand));
    if (!rawMap.isEmpty()) {
     rawMap.forEach((key, value) -> {
       ExecutableCommand command = Utility.convertStringCommandToObjExecutableCommand(value);
       userLastCommandMap.put(key, command);
     });
    }
    logger.info("В базе нет ниодного пользователя");
  }

  @Transactional
  public void updateFirstAndLastName(Long chatId, String firstName, String lastName) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setFirstName(firstName);
          user1.setLastName(lastName);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserPlace(Long chatId, String place) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setWorkingPlaces(place);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserLink(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setProfile(text);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserInterests(Long chatId, List<String> params) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setInterests(params);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserJob(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setPosition(text);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserGoal(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setGoals(text);
          repository.save(user1);
        });
  }

  @Transactional
  public void updateUserCurrentCommand(Long chatId, String text) {
    repository.findByChatId(chatId)
        .ifPresent(user1 -> {
          user1.setCurrentCommand(text);
          repository.save(user1);
        });
  }

  @Transactional
  public String getUserInfo(Long chatId) {
    Users user = repository.findByChatId(chatId).orElse(null);
    if (user == null) {
      return "Не удалось получить информацию о пользователе";
    } else {
      return user.toString();
    }
  }
}
