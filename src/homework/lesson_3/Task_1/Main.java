package homework.lesson_3.Task_1;

import java.util.Random;

public class Main {

    private static final String ERROR_SELECT_MENU = "Такого элемента меню не существует, введите номер меню";
    private static final String QUESTION_EXIT = "Вы уверены что ходите выйти из игры?";
    private static final String MENU_LITE_LEVEL = "Легкий уровень";
    private static final String MENU_HIGH_LEVEL = "Сложный уровень";
    private static final String GAME = "Угадайте число от %s до %s";
    private static final String ERROR_GAME = "Что-то пошло не так! Но ваша попытка защитана :)";
    private static final String MESSAGE_UPPER = "Мое значение больше";
    private static final String MESSAGE_LOWER = "Мое значение меньше";
    private static final String MESSAGE_YOU_WRONG = "Не угадал, ха ха.";

    public static void main(String[] args) {
        while (true){
            switch (SDKGame.dialog(ERROR_SELECT_MENU, CreateMenu())) {
                case "1":
                    playGame(false, 3);
                    break;
                case "2":
                    playGame(true, 3);
                    break;
                case "3":
                    if (SDKGame.question(QUESTION_EXIT))
                        return;
                    break;
            }
        }
    }

    private static String[][] CreateMenu() {
        return new String[][]{
                new String[]{"1", MENU_LITE_LEVEL},
                new String[]{"2", MENU_HIGH_LEVEL},
                new String[]{"3", SDKGame.MENU_EXIT}
        };
    }

    private static String[][] CreateExitMenu() {
        return new String[][]{
                new String[]{"1", "да", "повторить"},
                new String[]{"0", "нет"}
        };
    }

    private static void playGame(boolean highLevel, int attempts) {
        int computer = new Random().nextInt(10);
        int user = 0;
        boolean win = false;
        while (true) {
            SDKGame.printText(String.format(GAME, 0, 9));
            for (int attempt = 0; attempt < attempts; attempt++) {
                try {
                    user = SDKGame.scanner.nextInt();
                } catch (Exception err) {
                    SDKGame.scanner.next();
                    SDKGame.printErr(ERROR_GAME);
                    continue;
                }
                if (!highLevel)
                    SDKGame.printInfo(computer > user ? MESSAGE_UPPER : computer < user ? MESSAGE_LOWER : SDKGame.MESSAGE_YOU_WIN);
                else
                    SDKGame.printErr(MESSAGE_YOU_WRONG);
                win = computer == user;
                if (win)
                    break;
            }
            if (!win)
                SDKGame.printErr(SDKGame.MESSAGE_YOU_LOSE);
            if (!SDKGame.question(SDKGame.QUESTION_REPEAT, CreateExitMenu()[0], CreateExitMenu()[1]))
                return;
        }
    }
}
