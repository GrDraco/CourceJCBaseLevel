package homework.lesson_1;

public class Main {
    public static void main(String[] args){
        //---------Task-2----------
        int number = 10;
        long number_1 = 123;
        float number_2 = 15.2f;
        double number_3 = 22.3;
        String str = "Text";
        boolean flag = true;
        byte bip = 100;
        //---------Task-3----------
        int a = 5, b = 10, c = 12, d = 2;
        System.out.println(String.format("Task-3: %s * (%s+ (%s / %s) = %s",
                                         a, b, c, d, calulate(a, b, c, d)));
        //---------Task-4----------
        a = 5; b = 7;
        System.out.println(String.format("Task-4: %s + %s = %s isValid(%s)",
                                         a, b, a + b, sumIsValid(a, b)));
        //---------Task-5----------
        a = -2;
        System.out.print(String.format("Task-5: %s is ", a));
        printStatus(a);
        //---------Task-6----------
        a = -5;
        System.out.println(String.format("Task-6: %s isNegative(%s)",
                                       a, isNegative(a)));
        //---------Task-7----------
        System.out.print("Task-7: ");
        printHello("Игорь");
        //---------Task-8----------
        System.out.println("Task-8: ");
        printYearIsLeap(0);   // isLeap
        printYearIsLeap(1);   // isNot
        printYearIsLeap(4);   // isLeap
        printYearIsLeap(5);   // isNot
        printYearIsLeap(100); // isNot
        printYearIsLeap(150); // isNot
        printYearIsLeap(400); // isLeap
        printYearIsLeap(2004);// isLeap
        printYearIsLeap(2000);// isLeap
        printYearIsLeap(2100);// isNot
        printYearIsLeap(2400);// isLeap
    }
    public static int calulate(int a, int b, int c, int d){
        return a * (b + (c / d));
    }
    public static boolean sumIsValid(int num_1, int num_2){
        int sum = num_1 + num_2;
        return sum >= 10 && sum <= 20;
    }
    public static void printStatus(int num){
        System.out.println( (num >= 0 ? "positive" : "negative"));
    }
    public static boolean isNegative(int num){
        return num < 0;
    }
    public static void printHello(String name){
        System.out.println(String.format("Привет, %s!", name));
    }
    // Каждый 4-й и 400-й
    // Кроме каждого 100-го
    public static void printYearIsLeap(int year){
        boolean isLeap = year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
        System.out.println(String.format("%12s %s %s", "Year",
                                         year, isLeap ? "isLeap" : "isNot"));
    }
}