package bot.service;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.db.User;
import bot.models.enums.Commands;
import bot.utility.Utility;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bot.repository.DateCoffeeRepository;

@Service
public class UserDBService {
  private final Logger logger = LoggerFactory.getLogger(UserDBService.class);

  @Autowired
  private DateCoffeeRepository repository;

  private final Map<Long, ExecutableCommand> userStatus = getAllChatIdAndCurrentCommand();


  public User addUser(User user) {
    return repository.save(user);
  }

  public ExecutableCommand getUserCurrentCommand(Long chatId) {
    return userStatus.get(chatId);
  }

  public Map<Long, ExecutableCommand> getAllChatIdAndCurrentCommand() {
    Map<Long, String> rawMap = repository.getAllChatIdAndCurrentCommand();
    if (!rawMap.isEmpty()) {
      return Maps.transformValues(rawMap, Utility::convertStringCommandToObjExecutableCommand);
    }
    logger.info("В базе нет ниодного ползователя");
    return new HashMap<>();
  }

}
