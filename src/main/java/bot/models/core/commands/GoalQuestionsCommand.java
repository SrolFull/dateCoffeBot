package bot.models.core.commands;

import bot.handler.BotHandler;
import bot.models.core.ExecutableCommand;
import bot.models.enums.Commands;
import bot.models.enums.Goals;
import bot.models.enums.WorkingPlaces;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class GoalQuestionsCommand extends ExecutableCommand {

  @Override
  public Boolean isNeedWaitingResponse() {
    return true;
  }

  @Override
  public String getName() {
    return Commands.GOAL_QUESTION.getName();
  }

  @Override
  public String getCommandName() {
    return Commands.GOAL_QUESTION.getCommandName();
  }

  @Override
  public Logger getLogger() {
    return LoggerFactory.getLogger(GoalQuestionsCommand.class);
  }

  @Override
  public List<SendMessage> execute(Long chatId, String commandText) {
    BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(Commands.GOAL_QUESTION.getCommandText());
    sendMessage.setReplyMarkup(getKeyboardMarkup());
    return Collections.singletonList(sendMessage);
  }

  @Override
  public ExecutableCommand getNextCommand() {
    return Commands.FINAL_QUESTION.getCommand();
  }

  @Override
  public ExecutableCommand getPreviousCommand() {
    return Commands.WHATS_YOUR_JOB_QUESTION.getCommand();
  }

  @Override
  public ReplyKeyboard getKeyboardMarkup() {
    ReplyKeyboardMarkup keyboardMarkup =  new ReplyKeyboardMarkup();
    keyboardMarkup.setResizeKeyboard(true);
    keyboardMarkup.setOneTimeKeyboard(true);
    keyboardMarkup.setSelective(false);
    keyboardMarkup.setKeyboard(getKeyboardRows());
    return keyboardMarkup;
  }

  private List<KeyboardRow> getKeyboardRows(){
    List<KeyboardRow> keyboardRows = new LinkedList<>();
    Arrays.stream(Goals.values())
        .forEach(goal -> keyboardRows.add(addKeyboardRow(goal.getDescription())));
    return keyboardRows;
  }

  private KeyboardRow addKeyboardRow(String text) {
    KeyboardRow row = new KeyboardRow();
    row.add(text);
    return row;
  }
}
