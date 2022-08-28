package bot.service.impl;

import bot.models.db.Users;
import bot.models.enums.Goals;
import bot.models.enums.WorkingPlaces;
import bot.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public Users updateFirstAndLastName(Users user1, String firstName, String lastName) {
    user1.setFirstName(firstName);
    user1.setLastName(lastName);
    return user1;
  }

  @Override
  public Users updateUserPlace(Users user1, String place) {
    user1.setWorkingPlaces(place);
    return user1;
  }

  @Override
  public Users updateUserLink(Users user1, String text) {
    user1.setProfile(text);
    return user1;
  }

  @Override
  public Users updateUserInterests(Users user1, List<String> params) {
    user1.setInterests(params);
    return user1;
  }

  @Override
  public Users updateUserJob(Users user1, String text) {
    user1.setPosition(text);
    return user1;
  }

  @Override
  public Users updateUserGoal(Users user1, String text) {
    user1.setGoals(text);
    return user1;
  }
}
