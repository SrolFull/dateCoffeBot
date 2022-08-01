package bot.models.db;

import bot.models.enums.WorkingPlaces;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import models.enums.Goals;

@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String profile;
  @ElementCollection
  private Map<String, Boolean> interests;
  private String position;
  private Goals goals;
  private WorkingPlaces workingPlaces;
  private Long chatId;
}
