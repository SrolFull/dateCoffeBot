package bot.models.enums;

public enum Messages {
  START_MESSAGE("Привет! "
      + "\nЯ бот Random Coffee Блока Технологии в Екатеринбурге. "
      + "\nКаждую неделю я буду предлагать тебе для встречи человека, выбранного среди других участников нашего сообщества."
      + "\nЧтобы принять участие во встречах – нужно заполнить анкету, ответив на 6 вопросов ниже."
      + " Если что, ты всегда можешь поменять данные своего профиля при помощи команды \"/start\""
      + "\nПоехали!"),
  QUESTION_ONE("Вопрос 1 из 6."
      + "\nКак тебя зовут ? Напиши свое имя и фамилию."),
  QUESTION_TWO_TITLE("Вопрос 2 из 6."
      + "\nНа какой площадке ты работаешь ? Выбери из предложенных вариантов:"),
  QUESTION_THREE("Вопрос 3 из 6."
      + "\nПришли ссылку на профиль в соцсетях, который ты активно ведешь."
      + "\nЭто поможет собеседнику заочно познакомиться с тобой. Рекомендую оставить именно ссылку, а не никнейм,"
      + " чтобы не приходилось набирать вручную в строке поиска (пример, «Мой никнейм в инсте @lusha_a – придется"
      + " набирать в поиске, это не удобно» )"),
  QUESTION_FOUR_UP_TITLE("Вопрос 4 из 6."
      + "\nКакие у тебя есть рабочие и личные интересы?"
      + "\nВыбери минимум 3 интереса. "
      + "\nЯ постараюсь найти для тебя собеседника со схожими интересами,а если не найду таковых"
      + " – у вас будет возможность пообщаться о чем-то новеньком"),
  QUESTION_FIVE("Вопрос 5 из 6."
      + "\nКем ты работаешь ?"),
  QUESTION_SIX("Вопрос 6 из 6."
      + "\nНекоторые люди приходят на Random Coffee встречи, что бы найти партнеров для будущих"
      + " проектов и завести полезные контакты – назовем это «польза»."
      + " Кто-то приходит на встречи для расширения кругозора, новых эмоций - назовем это «фан»."
      + " Какое описание тебе подходит больше? Выбери из предложенных вариантов:"),
  QUESTION_HELP("Я овощ, мне нужна помощь");


  private final String message;

  Messages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
