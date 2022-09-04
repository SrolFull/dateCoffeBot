package bot.models.core;

import bot.handler.BotHandler;
import bot.models.enums.CallBackButtons;
import bot.models.enums.Commands;
import bot.models.enums.WritingButtons;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public abstract class ExecutableCommand {
 public abstract Boolean isNeedWaitingResponse();
 public abstract String getName();
 public abstract String getCommandName();
 public abstract String getMessageText(String commandText);
 public abstract Logger getLogger();
 public abstract Boolean needKeyboard();
 /**
  * @see WritingButtons
  * @return
  */
 public WritingButtons[] getWritingBtns() {
  return null;
 }
 public CallBackButtons[] getCallbackBtns() {
  return null;
 }
 public ExecutableCommand getNextCommand() {
  return null;
 }
 public ExecutableCommand getPreviousCommand(){
  return null;
 }

 /**
  * Execute command with ReplyKeyboard send message in chat
  * @see #getWritingBtns() you shold override this
  * @param chatId - ид чата
  * @param commandText - Команда/ответ на команду
  * @return
  */
 public List<SendMessage> execute(Long chatId, String commandText) {
  BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());
  SendMessage sendMessage = new SendMessage();
  sendMessage.setChatId(chatId);
  sendMessage.setText(getMessageText(commandText));
  if (!needKeyboard()) {
   return Collections.singletonList(sendMessage);
  }
  if (Commands.FINAL_QUESTION.getName().equals(this.getName())) {
   sendMessage.setReplyMarkup(getInlineKeyboardMarkup());
  } else {
   sendMessage.setReplyMarkup(getReplyKeyboardMarkup());
  }
  return Collections.singletonList(sendMessage);
 }

 /**
  * Execute command with InlineKeyboard return Query
  * @see #getCallbackBtns() you shold override this
  * @param chatId - ид чата
  * @param text - callback msg
  * @param id - id callbackQuery
  * @return
  */
 public List<EditMessageText> execute(Long chatId, String text, Integer id) {
  BotHandler.isWaitingQuestionAnswer.put(chatId, isNeedWaitingResponse());
  List<EditMessageText> answer = new LinkedList<>();

  EditMessageText editMessageText = new EditMessageText();
  editMessageText.setChatId(chatId);
  editMessageText.setMessageId(id);
  editMessageText.setText(getMessageText(text));
  if (needKeyboard()) {
   editMessageText.setReplyMarkup(getInlineKeyboardMarkup());
  }

  answer.add(editMessageText);
  return answer;
 }


 private ReplyKeyboard getReplyKeyboardMarkup() {
  ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
  keyboardMarkup.setResizeKeyboard(true);
  keyboardMarkup.setOneTimeKeyboard(true);
  keyboardMarkup.setSelective(false);
  keyboardMarkup.setKeyboard(getReplyKeyboardBtns());
  return keyboardMarkup;
 }

 private InlineKeyboardMarkup getInlineKeyboardMarkup() {
  InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
  keyboardMarkup.setKeyboard(getInlineKeyboardBtns());
  return keyboardMarkup;
 }


 private List<KeyboardRow> getReplyKeyboardBtns() {
  List<KeyboardRow> keyboardRows = new LinkedList<>();
  Arrays.stream(getWritingBtns())
      .map(WritingButtons::getText)
      .forEach(text -> keyboardRows.add(addKeyboardRow(text)));
  return keyboardRows;
 }

 private List<List<InlineKeyboardButton>> getInlineKeyboardBtns() {
  List<List<InlineKeyboardButton>> columns = new LinkedList<>(new LinkedList<>());
  Arrays.stream(getCallbackBtns())
      .forEach(enumBtn -> {
       InlineKeyboardButton button = new InlineKeyboardButton();
       button.setCallbackData(enumBtn.getAction());
       button.setText(enumBtn.getDescription());
       columns.add(Collections.singletonList(button));
      });
  return columns;
 }

 private KeyboardRow addKeyboardRow(String text) {
  KeyboardRow row = new KeyboardRow();
  row.add(text);
  return row;
 }
}
