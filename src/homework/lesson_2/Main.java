package homework.lesson_2;

import java.util.Arrays;

public class Main {
    //----Settings-for-output--
    private static final int PRINT_INDENT = 6;
    public static void main(String[] args) {
        System.out.println("---Course JavaCore (base level lesson_2)---");
        //---------Task-1----------
        printTask(1);
        int[] arr_1 = new int[] {1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        System.out.println("arr = " + Arrays.toString(arr_1));
        invertArray(arr_1);
        System.out.println("inv = " + Arrays.toString(arr_1));
        //---------Task-2----------
        printTask(2);
        int[] arr_2 = new int[8];
        System.out.println("arr = " + Arrays.toString(arr_2));
        fillArray(arr_2, 0, 3, 6, 9, 12, 15, 18, 21);
        System.out.println("arr = " + Arrays.toString(arr_2));
        //---------Task-3----------
        printTask(3);
        int[] arr_3 = new int[] { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("arr = " + Arrays.toString(arr_3));
        increaseValInArray(arr_3, 6, 2);
        System.out.println("arr = " + Arrays.toString(arr_3));
        //---------Task-4----------
        printTask(4);
        int[][] arr_4 = new int[5][5];
        printArraySquare(arr_4);
        // Первый способ
        fillArraySquare_1(arr_4, 1);
        printArraySquare(arr_4);
        // Второй способ
        fillArraySquare_2(arr_4, 2);
        printArraySquare(arr_4);
        //---------Task-5----------
        printTask(5);
        int[] arr_5 = new int[] { 1, 5, -3, 2, 11, 44, 25, 2, 4, 8, 9, 1, 0 };
        System.out.println("arr = " + Arrays.toString(arr_5));
        System.out.println("max = " + getMaxValue(arr_5));
        System.out.println("min = " + getMinValue(arr_5));
        //---------Task-6----------
        printTask(6);
        int[] arr_6 = new int[] { 2, 2, 2, 1, 2, 2, /*||*/ 10, 1 };
        System.out.println("arr = " + Arrays.toString(arr_6));
        System.out.println("sum exist= " + existSum(arr_6));
        int[] arr_6_1 = new int[] { 1, 1, 1, /*||*/ 2, 1 };
        System.out.println("arr = " + Arrays.toString(arr_6_1));
        System.out.println("sum exist= " + existSum(arr_6_1));
        //---------Task-7----------
        printTask(7);
        int[] arr_7 = new int[] { 0, 1, 2, 3, 4, 5 };
        System.out.println("arr = " + Arrays.toString(arr_7));
        shiftArray(arr_7, 2);
        System.out.println("arr = " + Arrays.toString(arr_7));
    }
    private static void printTask(int task) {
        if (task > 0) {
            System.out.println();
            System.out.println(String.format("TASK-%s:", task));
        }
    }
    private static void invertArray(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i] == 0 ? 1 :
                     arr[i] == 1 ? 0 : arr[i];
    }
    private static void fillArray(int[] arr, int... values) {
        if (arr == null || arr.length == 0 || values == null || values.length == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            if (i == values.length)
                return;
            arr[i] = values[i];
        }
    }
    private static void increaseValInArray(int[] arr, int valLimit, int factor){
        if (arr == null || arr.length == 0 || valLimit == 0 || factor == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < valLimit)
                arr[i] *= factor;
        }
    }
    private static void printArraySquare(int[][] arr) {
        System.out.println("arr:");
        for (int i = 0; i< arr.length; i++)
            System.out.println(Arrays.toString((arr[i])));
    }
    private static void fillArraySquare_1(int[][] arr, int xValue) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) {
            if (arr[i] == null || arr[i].length != arr.length ||
                arr[j] == null || arr[j].length != arr.length)
                return;
            arr[i][i] = xValue;
            arr[j][i] = xValue;
        }
    }
    private static void fillArraySquare_2(int[][] arr, int xValue) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            int j = arr.length - 1 - i;
            if (arr[i] == null || arr[i].length != arr.length ||
                arr[j] == null || arr[j].length != arr.length)
                return;
            arr[i][i] = xValue;
            arr[j][i] = xValue;
        }
    }
    private static int getMaxValue(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            max = Integer.max(arr[i], max);
        return max;
    }
    private static int getMinValue(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++)
            min = Integer.min(arr[i], min);
        return min;
    }
    private static int getSum(int[] arr, int startIndex) {
        if (arr == null || arr.length == 0 || startIndex == 0)
            return 0;
        int sum = 0;
        for (int i = startIndex; i < arr.length; i++)
            sum += arr[i];
        return sum;
    }
    private static boolean existSum(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sum == getSum(arr, i + 1))
                return true;
        }
        return false;
    }
    private static void simpleShiftArray(int[] arr) {
        int cut = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--)
            arr[i] = arr[i - 1];
        arr[0] = cut;
    }
    private static void simpleReverseShiftArray(int[] arr) {
        int cut = arr[0];
        for (int i = 0; i < arr.length -1; i++)
            arr[i] = arr[i + 1];
        arr[arr.length - 1] = cut;
    }
    private static void shiftArray(int[] arr, int shift) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < Math.abs(shift); i++)
            if(shift > 0)
                simpleShiftArray(arr);
            else
                simpleReverseShiftArray(arr);
    }
}
