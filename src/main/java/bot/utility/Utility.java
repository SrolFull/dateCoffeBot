package bot.utility;

import bot.models.core.ExecutableCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import bot.models.enums.Commands;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
  private static final Logger logger = LoggerFactory.getLogger(Utility.class);

  public static ExecutableCommand convertStringCommandToObjExecutableCommand(String commandName) {
    try {
      return Commands.getExecutableCommandByName(commandName);
    } catch (UndefinedCommandException exception) {
      logger.info("Не удалось преобразовать строковую комманду в обьект \n");
      logger.info("Вернули комманду старт");
      return Commands.START.getCommand();
    }
  }

  public static Collection<ExecutableCommand> convertStringCommandsToObjExecutableCommands(Collection<String> commandsString) {
    return commandsString.stream()
        .map(Utility::convertStringCommandToObjExecutableCommand)
        .collect(Collectors.toList());
  }

  public static String convertListToStringWithDelimiter(List<String> list, String delimeter) {
    StringBuilder sb = new StringBuilder();
    list.forEach(element -> {
      sb.append(element);
      sb.append(delimeter);
    });
    return sb.toString();
  }

  public static List<String> convertStringToList(String input) {
    return Arrays.stream(input.split(","))
        .map(Utility::removeWhiteSpace)
        .collect(Collectors.toList());
  }

  private static String removeWhiteSpace(String str) {
    return str.replace(" ","");
  }
}
