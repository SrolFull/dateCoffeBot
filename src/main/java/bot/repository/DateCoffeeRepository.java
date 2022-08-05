package bot.repository;


import bot.models.db.User;
import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DateCoffeeRepository extends JpaRepository<User, Long> {
  User deleteUserById(Long id);

  @Query("select chatId, currentCommand from User")
  HashMap<Long, String> getAllChatIdAndCurrentCommand();
}
