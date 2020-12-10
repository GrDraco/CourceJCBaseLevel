package homework.lesson_4.game_x;

import homework.lesson_3.Task_1.SDKGame;
import java.util.Random;

public class Main {

    private static final int DEF_SIZE = 3;
    private static final int DEF_VICTORY_SIZE_POINTS = 3;
    private static final int DEF_SIZE_MIN = 3;
    private static final int DEF_SIZE_MAX = 5;
    private static final char DEF_EMPTY = '•';
    private static final char DEF_TURN_X = 'x';
    private static final char DEF_TURN_O = 'o';
    private static final String MESSAGE_INPUT_X = "введите X";
    private static final String MESSAGE_INPUT_Y = "введите Y";
    private static final String ERROR_TURN = "Данная ячейка занята выбирите другую";
    private static final String QUASTION_SIZE = "Укажите размер поля от %s до %s";
    private static final String QUASTION_VICTORY_SIZE_POINTS = "Укажите кол-во победных фишек от %s до %s";


    private static char[][] gamePanel;
    private static int panelSize;
    private static int sizeVictoryPoints;

    public static void main(String[] args) {
        while (true){
            switch (SDKGame.dialog(SDKGame.ERROR_SELECT_MENU, CreateMenu())) {
                case "1":
                    panelSize = SDKGame.dialogInt(String.format(QUASTION_SIZE, DEF_SIZE_MIN, DEF_SIZE_MAX), DEF_SIZE_MIN, DEF_SIZE_MAX);
                    if(panelSize == DEF_VICTORY_SIZE_POINTS)
                        sizeVictoryPoints = DEF_VICTORY_SIZE_POINTS;
                    else
                        sizeVictoryPoints = SDKGame.dialogInt(String.format(QUASTION_VICTORY_SIZE_POINTS, DEF_VICTORY_SIZE_POINTS, panelSize), DEF_VICTORY_SIZE_POINTS, panelSize);
                    gamePanel = createGamePanel(DEF_EMPTY);
                    playGame();
                    break;
                case "2":
                    if (SDKGame.question(SDKGame.QUESTION_EXIT))
                        return;
                    break;
            }
        }
    }

    private static String[][] CreateMenu() {
        return new String[][]{
                new String[]{"1", SDKGame.MENU_START_GAME},
                new String[]{"2", SDKGame.MENU_EXIT}
        };
    }

    public static void playGame() {
        boolean victoryUser = false;
        boolean victoryAi = false;
        int[] user, AI;
        SDKGame.printGrid(gamePanel, true);
        do {
            user = turnUser();
            gamePanel = refreshGamePanel(user, DEF_TURN_X);
            SDKGame.printGrid(gamePanel, true);
            victoryUser = checkVictory(user[0], user[1], DEF_TURN_X);
            if (victoryUser)
                break;
            AI = turnAINormal(user[0], user[1], DEF_TURN_X);
            gamePanel = refreshGamePanel(AI, DEF_TURN_O);
            SDKGame.printGrid(gamePanel, true);
            victoryAi = checkVictory(AI[0], AI[1], DEF_TURN_O);
        } while (!victoryUser && !victoryAi && !checkFinish());

        if (victoryUser)
            SDKGame.printInfo(SDKGame.MESSAGE_YOU_WIN);
        else if (victoryAi)
            SDKGame.printErr(SDKGame.MESSAGE_YOU_LOSE);
        else
            SDKGame.printErr(SDKGame.MESSAGE_DRAW);
    }

    public static boolean checkFinish() {
        int countEmpty = 0;
        for (int y = 0; y < gamePanel.length; y++) {
            for (int x = 0; x < gamePanel.length; x++)
                countEmpty += gamePanel[y][x] == DEF_EMPTY ? 1 : 0;
        }
        return  countEmpty == 0;
    }

    // Диагональ
    public static int calculateDiagonalPoints(int lastY, int lastX, char turn) {
        int diagonalPoints = gamePanel[lastY][lastX] == turn ? 1 : 0;
        boolean diagonalRightBreak = false;
        boolean diagonalLeftBreak = false;
        for (int i = 0, y = lastY + 1, backY = lastY - 1, x = lastX + 1, backX = lastX - 1; i < sizeVictoryPoints - 1; i++, y++, backY--, x++, backX--) {
            // Считаем в право
            if (!diagonalRightBreak && y > 0 && y < gamePanel.length && x > 0 && x < gamePanel.length) {
                if (gamePanel[y][x] == turn)
                    diagonalPoints++;
                else
                    diagonalRightBreak = true;
            }
            else
                diagonalRightBreak = true;
            // Считаем в лево
            if (!diagonalLeftBreak && backY >= 0 && backY < gamePanel.length && backX >= 0 && backX < gamePanel.length) {
                if (gamePanel[backY][backX] == turn)
                    diagonalPoints++;
                else
                    diagonalLeftBreak = true;
            }
            else
                diagonalLeftBreak = true;
        }
        //SDKGame.printInfo(String.format("diagonal => %s: %s", turn, diagonalPoints));
        return diagonalPoints;
    }

    // Обратная диагональ
    public static int calculateRevDiagonalPoints(int lastY, int lastX, char turn) {
        int revDiagonalPoints = gamePanel[lastY][lastX] == turn ? 1 : 0;
        boolean revDiagonalRightBreak = false;
        boolean revDiagonalLeftBreak = false;
        for (int i = 0, y = lastY - 1, backY = lastY + 1, x = lastX + 1, backX = lastX - 1; i < sizeVictoryPoints - 1; i++, y--, backY++, x++, backX--) {
            // Считаем в право
            if (!revDiagonalRightBreak && y >= 0 && y < gamePanel.length && x >= 0 && x < gamePanel.length) {
                if (gamePanel[y][x] == turn)
                    revDiagonalPoints++;
                else
                    revDiagonalRightBreak = true;
            }
            else
                revDiagonalRightBreak = true;
            // Считаем в лево
            if (!revDiagonalLeftBreak && backY > 0 && backY < gamePanel.length && backX >= 0 && backX < gamePanel.length) {
                if (gamePanel[backY][backX] == turn)
                    revDiagonalPoints++;
                else
                    revDiagonalLeftBreak = true;
            }
            else
                revDiagonalLeftBreak = true;
        }
        //SDKGame.printInfo(String.format("rev diagonal => %s: %s", turn, revDiagonalPoints));
        return revDiagonalPoints;
    }

    // Горизонталь
    public static int calculateHorizontalPoints(int lastY, int lastX, char turn) {
        int horizontalPoints = gamePanel[lastY][lastX] == turn ? 1 : 0;
        boolean horizontalRightBreak = false;
        boolean horizontalLeftBreak = false;
        for (int i = 0, x = lastX + 1, backX = lastX - 1; i < sizeVictoryPoints - 1; i++, x++, backX--) {
            // Считаем в право
            if (!horizontalRightBreak && x > 0 && x < gamePanel.length) {
                if (gamePanel[lastY][x] == turn)
                    horizontalPoints++;
                else
                    horizontalRightBreak = true;
            }
            else
                horizontalRightBreak = true;
            // Считаем в лево
            if (!horizontalLeftBreak && backX >= 0 && backX < gamePanel.length) {
                if (gamePanel[lastY][backX] == turn)
                    horizontalPoints++;
                else
                    horizontalLeftBreak = true;
            }
            else
                horizontalLeftBreak = true;
        }
        //SDKGame.printInfo(String.format("horizontal => %s: %s", turn, horizontalPoints));
        return horizontalPoints;
    }

    // Вертикаль
    public static int calculateVerticalPoints(int lastY, int lastX, char turn) {
        int verticalPoints = gamePanel[lastY][lastX] == turn ? 1 : 0;
        boolean verticalRightBreak = false;
        boolean verticalLeftBreak = false;
        for (int i = 0, y = lastY + 1, backY = lastY - 1; i < sizeVictoryPoints - 1; i++, y++, backY--) {
            // Считаем в право
            if (!verticalRightBreak && y > 0 && y < gamePanel.length) {
                if (gamePanel[y][lastX] == turn)
                    verticalPoints++;
                else
                    verticalRightBreak = true;
            }
            else
                verticalRightBreak = true;
            // Считаем в лево
            if (!verticalLeftBreak && backY >= 0 && backY < gamePanel.length) {
                if (gamePanel[backY][lastX] == turn)
                    verticalPoints++;
                else
                    verticalLeftBreak = true;
            }
            else
                verticalLeftBreak = true;
        }
        //SDKGame.printInfo(String.format("vertical => %s: %s", turn, verticalPoints));
        return verticalPoints;
    }

    public static boolean checkVictory(int lastY, int lastX, char turn) {
        return calculateDiagonalPoints(lastY, lastX, turn) >= sizeVictoryPoints ||
               calculateRevDiagonalPoints(lastY, lastX, turn) >= sizeVictoryPoints ||
               calculateHorizontalPoints(lastY, lastX, turn) >= sizeVictoryPoints ||
               calculateVerticalPoints(lastY, lastX, turn) >= sizeVictoryPoints;
    }

    public static boolean checkTurn(int[] turn) {
        try {
            return gamePanel[turn[0]][turn[1]] == DEF_EMPTY;
        }
        catch (Exception err) {
            return false;
        }
    }

    public static char[][] refreshGamePanel(int[] turn, char inputChar) {
        if (gamePanel == null || gamePanel.length == 0)
            return createGamePanel(DEF_EMPTY);
        if (turn == null || turn.length < 2)
            return gamePanel;
        if (checkTurn(turn))
            gamePanel[turn[0]][turn[1]] = inputChar;
        return gamePanel;
    }

    // answer[0] - Y
    // answer[1] - X
    public static int[] turnAIStupid() {
        int[] answer = new int[2];
        int index = 0;
        SDKGame.printText(SDKGame.MESSAGE_LOADING);
        do {
            answer[0] = new Random().nextInt(panelSize);
            answer[1] = new Random().nextInt(panelSize);
            index++;
        } while (!checkTurn(answer) && index < panelSize * 100);
        return answer;
    }
    // answer[0] - Y
    // answer[1] - X
    public static int[] turnAINormal(int lastY, int lastX, char turn) {
        SDKGame.printText(SDKGame.MESSAGE_LOADING);
        int[] directions = { calculateDiagonalPoints(lastY, lastX, turn),
                             calculateRevDiagonalPoints(lastY, lastX, turn),
                             calculateHorizontalPoints(lastY, lastX, turn),
                             calculateVerticalPoints(lastY, lastX, turn) };
        switch (getTurnByDirection(directions)) {
            case 0 :
                return turnAIDiagonal(lastY, lastX);
            case 1 :
                return turnAIRevDiagonal(lastY, lastX);
            case 2 :
                return turnAIHorizontal(lastY, lastX);
            case 3 :
                return turnAIVertical(lastY, lastX);
        }
        return turnAIStupid();
    }

    public static int getTurnByDirection(int[] values) {
        int maxValue = 0;
        int maxIndex = 0;
        boolean isEqual = true;
        // Определяем максимальную вероятност хода
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxIndex = i;
            }
            if (values[i] != maxValue)
                isEqual  = false;
        }
        // Если вероятност ьодинаковая то напрваление выбирвем случайно
        if (isEqual)
            return new Random().nextInt(4);
        return maxIndex;
    }

    public static int[] turnAIDiagonal(int lastY, int lastX) {
        int[] answer = new int[] { lastY + 1, lastX + 1 };
        if (checkTurn(answer))
            return answer;
        else {
            answer = new int[] { lastY - 1, lastX - 1 };
            if (checkTurn(answer))
                return answer;
            else
                return turnAIStupid();
        }
    }

    public static int[] turnAIRevDiagonal(int lastY, int lastX) {
        int[] answer = new int[] { lastY + 1, lastX - 1 };
        if (checkTurn(answer))
            return answer;
        else {
            answer = new int[] { lastY + 1, lastX + 1 };
            if (checkTurn(answer))
                return answer;
            else
                return turnAIStupid();
        }
    }

    public static int[] turnAIHorizontal(int lastY, int lastX) {
        int[] answer = new int[] { lastY, lastX + 1 };
        if (checkTurn(answer))
            return answer;
        else {
            answer = new int[] { lastY, lastX - 1 };
            if (checkTurn(answer))
                return answer;
            else
                return turnAIStupid();
        }
    }

    public static int[] turnAIVertical(int lastY, int lastX) {
        int[] answer = new int[] { lastY + 1, lastX };
        if (checkTurn(answer))
            return answer;
        else {
            answer = new int[] { lastY - 1, lastX };
            if (checkTurn(answer))
                return answer;
            else
                return turnAIStupid();
        }
    }

    // answer[0] - Y
    // answer[1] - X
    public static int[] turnUser() {
        int[] answer = new int[2];
        boolean fail = true;
        do {
            answer[0] = SDKGame.dialogInt(SDKGame.MESSAGE_YOUR_TURN + " " + MESSAGE_INPUT_Y, 1, panelSize) - 1;
            answer[1] = SDKGame.dialogInt(SDKGame.MESSAGE_YOUR_TURN + " " + MESSAGE_INPUT_X, 1, panelSize) - 1;
            fail = !checkTurn(answer);
            if (fail)
                SDKGame.printErr(ERROR_TURN);
        } while (fail);
        return answer;
    }

    public static char[][] createGamePanel(char empty) {
        panelSize = panelSize > DEF_SIZE ? panelSize : DEF_SIZE;
        char[][] gamePanel = new char[panelSize][panelSize];
        for (int y = 0; y < gamePanel.length; y++)
            for (int x = 0; x < gamePanel.length; x++) {
                gamePanel[y][x] = empty;
            }
        return gamePanel;
    }
}
