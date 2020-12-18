package homework.lesson_6;

public class Main {
    public static void main(String[] args) {
        Dog dog_1 = new Dog("dog_1");
        Dog dog_2 = new Dog("dog_2");
        Cat cat_1 = new Cat("cat_1");
        Cat cat_2 = new Cat("cat_2");

        dog_1.run(500);
        dog_1.jump(0.5);
        dog_1.swim(20);

        dog_2.run(600);
        dog_2.jump(1);
        dog_2.swim(5);

        cat_1.run(100);
        cat_1.jump(1.5);

        cat_1.run(250);
        cat_1.jump(5);
    }
}
