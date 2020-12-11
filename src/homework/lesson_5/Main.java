package homework.lesson_5;

public class Main {
    public static void main(String[] args) throws Exception {
        Person[] persons = new Person[] {
                new Person("Петров Петр Петрович", "Директор", "petr@mail.ru", "899922255511", 200000, 45),
                new Person("Сидоров Семем Семенович", "Бухгалтер", "sid@mail.ru", "899922255522", 150000, 32),
                new Person("Пупков Виктор Викторович", "Программист", "pup@mail.ru", "899922255533", 100000, 27),
                new Person("Шнуров Вадим Вадимович", "Аналитик", "shn@mail.ru", "899922255544", 70000, 28),
                new Person("Пушков Олег Олегович", "Тестировщик", "push@mail.ru", "899922255555", 70000, 25),
        };
        for (Person person: persons) {
            if(person.getAge() > 40) {
                person.printIntoConsoleAllProperty();
                System.out.println("-----------------------------");
            }
        }
    }
}
