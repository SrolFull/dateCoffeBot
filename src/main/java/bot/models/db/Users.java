package bot.models.db;

import bot.models.enums.WorkingPlaces;
import bot.utility.Utility;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import bot.models.enums.Goals;
import javax.persistence.Table;
import lombok.Setter;

@Setter
@Entity
@Table(name="tgUsers")
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "profile")
  private String profile;
  @ElementCollection()
  @Column(name = "interests")
  private List<String> interests;
  @Column(name = "position")
  private String position;
  @Column(name = "goals")
  private String goals;
  @Column(name = "workinPlace")
  private String workingPlaces;
  @Column(name = "chat_id")
  private Long chatId;
  @Column(name = "current_command")
  private String currentCommand;

  public Users(Long chatId) {
    this.chatId = chatId;
  }

  @Column(name = "chat_id")
  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getProfile() {
    return profile;
  }

  public List<String> getInterests() {
    return interests;
  }

  public String getPosition() {
    return position;
  }

  public String getGoals() {
    return goals;
  }

  public String getWorkingPlaces() {
    return workingPlaces;
  }

  public Long getChatId() {
    return chatId;
  }

  public String getCurrentCommand() {
    return currentCommand;
  }

  @Override
  public String toString() {
    return "\nИмя" + firstName
        + "\nФамиля" + lastName
        + "\nСсылка на профиль" + profile
        + "\nИнтересы" + Utility.convertListToStringWithDelimiter(interests, ", ")
        + "\nТы работаешь" + position
        + "\nМесто работы" + workingPlaces
        + "\nЦель встречи" + goals;
  }
}
