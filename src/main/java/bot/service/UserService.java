package bot.service;

import bot.models.db.Users;

public interface UserService {
  public Users updateFirstAndLastName(Users user1, String firstName, String lastName);
}
