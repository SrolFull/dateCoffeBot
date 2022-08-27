package bot.models.db;

import bot.models.enums.WorkingPlaces;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import bot.models.enums.Goals;

@Data
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String profile;
  @ElementCollection()
  private Map<String, Boolean> interests;
  private String position;
  private Goals goals;
  private WorkingPlaces workingPlaces;
  private Long chatId;
  private String currentCommand;
}
