package bot.service.impl;

import bot.models.db.Users;
import bot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public Users updateFirstAndLastName(Users user1, String firstName, String lastName) {
    user1.setFirstName(firstName);
    user1.setLastName(lastName);
    return user1;
  }
}
