package bot.repository;


import bot.models.db.Users;
import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface DateCoffeeRepository extends JpaRepository<Users, Long> {
  Users deleteUserById(Long id);

  @Query(value = "select chatId from Users")
  HashMap<Long, String> getAllChatIdAndCurrentCommand();
}
