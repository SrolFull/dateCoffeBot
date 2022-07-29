package bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BotConfig {
  @Value("${bot.name}")
  private String name;
  @Value("${bot.token}")
  private String token;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
