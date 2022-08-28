package bot.service;

import bot.models.db.Users;
import java.util.List;

public interface UserService {
  Users updateFirstAndLastName(Users user1, String firstName, String lastName);
  Users updateUserPlace(Users users1, String place);

  Users updateUserLink(Users user1, String text);

  Users updateUserInterests(Users user1, List<String> params);

  Users updateUserJob(Users user1, String text);

  Users updateUserGoal(Users user1, String text);
}
