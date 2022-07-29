package bot.repository;


import bot.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateCoffeeRepository extends JpaRepository<User, Long> {
  User deleteUserById(Long id);
}
