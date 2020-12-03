package homework.lesson_3.Task_2;

import homework.lesson_3.Task_1.SDKGame;
import java.util.Random;

public class Main {

    private static final String GAME = "Угадате какое слово я загадал:";
    private static final String MESSAGE_WORD = "Я готов открыть буквы которые Вы угадали:";
    private static final String MESSAGE_INPUT = "Ведите ваш вариант:";
    private static final String MASK = "#";
    private static final int MASK_LENGTH = 15;
    private static final String[] WORDS = { "apple","orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                                            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                                            "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
                                            "pumpkin", "potato" };

    public static void main(String[] args) {
        while (true){
            switch (SDKGame.dialog(SDKGame.ERROR_SELECT_MENU, CreateMenu())) {
                case "1":
                    playGame(generateWord());
                    break;
                case "2":
                    if (SDKGame.question(SDKGame.QUESTION_EXIT))
                        return;
                    break;
            }
        }
    }

    private static void playGame(String word) {
        SDKGame.printText(GAME);
        String answer = "";
        while (true) {
            SDKGame.printText(MESSAGE_INPUT, false);
            answer = SDKGame.scanner.next();
            SDKGame.printText(MESSAGE_WORD);
            printWord(word, answer);
            if (word.equals(answer.toLowerCase())) {
                SDKGame.printInfo(SDKGame.MESSAGE_YOU_WIN);
                return;
            }
        }
    }

    private static String generateWord() {
        return WORDS[new Random().nextInt(WORDS.length)];
    }

    private static void printWord(String word, String wordUser) {
        String result = "";
        for (int i = 0; i < MASK_LENGTH; i++) {
            if (i < word.length() && i < wordUser.length() &&
                word.charAt(i) == wordUser.charAt(i))
                result += word.toCharArray()[i];
            else
                result += MASK;
        }
        SDKGame.printText(result);
    }

    private static String[][] CreateMenu() {
        return new String[][]{
                new String[]{"1", SDKGame.MENU_START_GAME},
                new String[]{"2", SDKGame.MENU_EXIT}
        };
    }
}
