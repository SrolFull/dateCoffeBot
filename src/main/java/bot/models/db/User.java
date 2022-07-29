package bot.models.db;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import models.enums.Goals;
import models.enums.WorkingPlaces;

@Entity
@Getter
@Setter
public class User {
  @Id
  private Long id;
  private String firstName;
  private String lastName;
  private String profile;
  @ElementCollection()
  private List<String> interests;
  private String position;
  private Goals goals;
  private WorkingPlaces workingPlaces;
}
