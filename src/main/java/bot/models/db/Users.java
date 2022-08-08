package bot.models.db;

import bot.models.enums.WorkingPlaces;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import bot.models.enums.Goals;

@Data
@Entity
@Table(name = "users", schema = "tg_users")
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String profile;
  @ElementCollection()
  private Map<String, Boolean> interests;
  private String position;
  private Goals goals;
  private WorkingPlaces workingPlaces;
  @Column(name = "chat_id")
  private Long chatId;
  @Column(name = "current_command")
  private String currentCommand;
}
