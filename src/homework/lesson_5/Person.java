package homework.lesson_5;

public class Person {
    private String fio;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;
    Person(String fio, String position, String email, String phone, double salary, int age) throws Exception {
        if (fio == null || fio.isEmpty() || position == null || position.isEmpty() || email == null ||
            email.isEmpty() || phone == null || phone.isEmpty() ||salary <= 0 || age <= 0 || age < 18)
            throw new Exception("Не корректно заданы параметры конструктора класса Person");
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void printIntoConsoleAllProperty() {
        System.out.println(this);
    }
    @Override
    public String toString() {
        return String.format("Сотрудник (%s)\n должность: %s\n email: %s\n телефон: %s\n зарплата: %,g руб.\n возраст: %s лет",
                             fio, position, email, phone, salary, age);
    }
}
