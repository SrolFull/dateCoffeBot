package bot.repository;


import bot.models.db.IUserCommands;
import bot.models.db.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DateCoffeeRepository extends JpaRepository<Users, Long> {
  Users deleteUsersByChatId(Long id);

  @Query(value = "select chatId as id, currentCommand as command from Users")
  List<IUserCommands> getAllChatIdAndCurrentCommand();

  Optional<Users> findByChatId(Long chatId);
}
