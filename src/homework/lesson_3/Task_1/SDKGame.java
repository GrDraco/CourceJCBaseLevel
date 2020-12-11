package homework.lesson_3.Task_1;

import java.util.Scanner;

public class SDKGame {

    private static final String CHOSE_ACTION = "Выберите действие: ";
    private static final String ERROR_INPUT = "Ошибка ввода";
    private static final String ERROR_MENU = "Ошибка формирования диалога, проверьте параметры диалога";
    private static final String ERROR_EXIT = "Я Вас не понимаю, скаэите \"да(1)\" или \"нет(0)\"";

    public static final String MENU_START_GAME = "Начать игру";
    public static final String MENU_EXIT = "Выйти";
    public static final String ERROR_SELECT_MENU = "Такого элемента меню не существует, введите номер меню";
    public static final String QUESTION_EXIT = "Вы уверены что ходите выйти из игры?";
    public static final String QUESTION_REPEAT = "Повторить игру еще раз?";
    public static final String MESSAGE_YOU_WIN = "Поздравляю Вы победили, УРА УРА УРА !!!";
    public static final String MESSAGE_YOU_LOSE = "Вы проиграли";
    public static final String MESSAGE_DRAW = "Ничья";
    public static final String MESSAGE_YOUR_TURN = "Ваш ход";
    public static final String MESSAGE_LOADING = "Думаю ...";

    public static Scanner scanner = new Scanner(System.in);

    public static boolean question(String question) {
        return question(question,"1", "y", "+", "yes", "да", "д");
    }

    public static boolean question(String question, String... yesAnswers) {
        // Проверяем на видность параметров диалога
        if(question == null || question.isEmpty()) {
            printErr(ERROR_MENU);
            return false;
        }
        // Диалог
        String answer = "";
        while (true) {
            // Выводим диалог
            printText(question);
            // Считываем ввод пользователя
            answer = scanner.next();
            // Определяем ответ пользователя
            if (checkAnswer(answer, yesAnswers))
                return true;
            return false;
        }
    }

    public static boolean question(String question, String[] yesAnswers, String[] noAnswers) {
        // Проверяем на видность параметров диалога
        if(question == null || question.isEmpty() ||
                yesAnswers == null || yesAnswers.length == 0 ||
                noAnswers == null || noAnswers.length == 0) {
            printErr(ERROR_MENU);
            return false;
        }
        // Диалог
        String answer = "";
        while (true) {
            // Выводим диалог
            printText(question);
            // Считываем ввод пользователя
            answer = scanner.next();
            // Определяем ответ пользователя
            if (checkAnswer(answer, yesAnswers))
                return true;
            if (checkAnswer(answer, noAnswers))
                return false;
            printInfo(ERROR_EXIT);
        }
    }

    private static boolean checkAnswer(String answer, String... answers) {
        if(answer == null || answer.isEmpty() || answers == null || answers.length <= 1)
            return false;
        for (String answerItem : answers) {
            if (answer.toLowerCase().equals(answerItem.toLowerCase()))
                return true;
        }
        return false;
    }

    public static int dialogInt(String message, int min, int max) {
        int answer = 0;
        boolean fail = true;
        do {
            printText(String.format("%s: ", message), false);
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                fail = !(answer >= min && answer <= max);
            } else
                scanner.next();
            if (fail)
                printErr(ERROR_INPUT);
        } while (fail);
        return answer;
    }

    public static String dialog(String[]... menu) {
        return dialog(null, menu);
    }

    public static String dialog(String errMsg, String[]... menu) {
        // Проверяем на видность параметров диалога
        if(!checkMenu(menu))
            return null;
        // Диалог
        String selected = "";
        while (true) {
            // Выводим диалог
            printText(CHOSE_ACTION);
            for (String[] menuItem : menu)
                printMenuItem(menuItem[0], menuItem[1]);
            // Считываем ввод пользователя
            selected = scanner.next();
            // Проверяем корректность ввода
            if(checkMenuSelected(selected, menu))
                return selected;
            // Сообщаем об ошибке ввода
            printErr(errMsg != null && !errMsg.isEmpty() ?
                    String.format("%s (%s)", ERROR_INPUT, errMsg) :
                    ERROR_INPUT);
        }
    }

    private static void printMenuItem(String index, String text) {
        System.out.println(String.format("%s. %s", index, text));
    }

    private static boolean checkMenu(String[]... menu) {
        if(menu == null || menu.length <= 1) {
            printErr(ERROR_MENU);
            return false;
        }
        String indexes = "";
        for (String[] menuItem : menu) {
            if (menuItem.length <= 1 || indexes.contains(String.format("_%s_", menuItem[0]))) {
                printErr(ERROR_MENU);
                return false;
            }
            indexes += String.format("_%s_", menuItem[0]);
        }
        return true;
    }

    private static boolean checkMenuSelected(String selected, String[]... menu) {
        if(selected == null || selected.isEmpty() || menu == null || menu.length <= 1)
            return false;
        for (String[] menuItem : menu) {
            if (selected.equals(menuItem[0]))
                return true;
        }
        return false;
    }

    public static void printText(String message) {
        printText(message, true);
    }

    public static void printText(String message, boolean ln) {
        printBaseColor();
        if(ln)
            System.out.println(message);
        else
            System.out.print(message);
    }

    public static void printInfo(String message) {
        System.out.print("\u001B[33m");
        System.out.println(message);
        printBaseColor();
    }

    public static void printErr(String message) {
        System.out.print("\u001B[31m");
        System.out.println(message);
        printBaseColor();
    }

    public static void printBaseColor() {
        System.out.print("\u001B[34m");
    }

    public static void printGrid(char[][] grid, boolean viewAxes) {
        if(grid == null || grid.length == 0)
            return;
        SDKGame.printText("# ",false);
        for (int x = 0; x < grid.length; x++)
            SDKGame.printText((viewAxes ? (x + 1) : "") + " ", x == grid.length - 1);

        for (int y = 0; y < grid.length; y++) {
            SDKGame.printText((viewAxes ? (y + 1) : "") + " ", false);
            for (int x = 0; x < grid.length; x++) {
                SDKGame.printText(Character.toString(grid[y][x]), false);
                SDKGame.printText(" ", x == grid.length - 1);
            }
        }
    }
}
