package bot.models.enums;

import bot.models.core.ExecutableCommand;
import bot.models.core.commands.FinalQuestionCommand;
import bot.models.core.commands.GoalQuestionsCommand;
import bot.models.core.commands.HelpCommand;
import bot.models.core.commands.InterestsQuestionCommand;
import bot.models.core.commands.JobQuestionsCommand;
import bot.models.core.commands.LinkQuestionCommand;
import bot.models.core.commands.MeetingCommand;
import bot.models.core.commands.PlaceQuestionCommand;
import bot.models.core.commands.SaveUserCommand;
import bot.models.core.commands.StartCommand;
import bot.models.core.exceptions.UndefinedCommandException;
import java.util.Arrays;

public enum Commands {
  START("Старт общения","/start", new StartCommand(), Messages.START_MESSAGE.getMessage()),
  HELP("Справочник", "/help", new HelpCommand(), Messages.QUESTION_HELP.getMessage()),
  MEETING("Знакомство", "/meeting", new MeetingCommand(), Messages.QUESTION_ONE.getMessage()),
  PLACE_QUESTION("Место работы","place", new PlaceQuestionCommand(), Messages.QUESTION_TWO_TITLE.getMessage()),
  LINK_QUESTION("Ссылки","links", new LinkQuestionCommand(), Messages.QUESTION_THREE.getMessage()),
  INTERESTS_QUESTION("Интересы","interests", new InterestsQuestionCommand(), Messages.QUESTION_FOUR.getMessage()),
  WHATS_YOUR_JOB_QUESTION("Работа","job" ,new JobQuestionsCommand(), Messages.QUESTION_FIVE.getMessage()),
  GOAL_QUESTION("Цель","goal", new GoalQuestionsCommand(), Messages.QUESTION_SIX.getMessage()),
  FINAL_QUESTION("Завершение опроса", "final", new FinalQuestionCommand(), Messages.QUESTION_FINAL.getMessage()),
  SAVE_USER("Сохранения данных пользователя", "save", new SaveUserCommand(), Messages.SAVE_USER_INFO.getMessage());

  private final String description;
  private final String commandName;
  private final ExecutableCommand command;
  private final String commandText;

  public static ExecutableCommand getExecutableCommandByName(String name) throws UndefinedCommandException {
    Commands command = getCommandByCommandName(name);
    return command.getCommand();
  }

  public static Commands getCommandByCommandName(String name) throws UndefinedCommandException {
    Commands command =  Arrays.stream(Commands.values())
        .filter(v -> v.getCommandName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
    if (command == null) {
      throw new UndefinedCommandException(name);
    }
    return command;
  }

  Commands(String description, String commandName, ExecutableCommand command, String commandText) {
    this.description = description;
    this.commandName = commandName;
    this.command = command;
    this.commandText = commandText;
  }


  public String getName() {
    return this.name();
  }

  public ExecutableCommand getCommand() {
    return command;
  }

  public String getCommandName() {
    return commandName;
  }

  public String getCommandText() {
    return commandText;
  }
}
