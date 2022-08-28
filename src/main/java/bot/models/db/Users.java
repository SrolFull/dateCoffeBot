package bot.models.db;

import bot.models.enums.WorkingPlaces;
import bot.utility.Utility;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import bot.models.enums.Goals;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.StringUtils;

@Getter
@Setter
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String profile;
  @ElementCollection()
  private List<String> interests;
  private String position;
  private Goals goals;
  private WorkingPlaces workingPlaces;
  private Long chatId;
  private String currentCommand;

  @Override
  public String toString() {
    return "\nИмя" + firstName
        + "\nФамиля" + lastName
        + "\nСсылка на профиль" + profile
        + "\nИнтересы" + Utility.convertListToStringWithDelimiter(interests, ", ")
        + "\nТы работаешь" + position
        + "\nМесто работы" + workingPlaces.getAddress()
        + "\nЦель встречи" + goals.getDescription();
  }
}
