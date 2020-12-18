package homework.lesson_6;

import java.util.Random;

public class Dog extends Animal implements ISwiming{
    protected double limitSwim = 10;

    public Dog(String name) {
        super(name, new Random().nextInt(201) + 400, 0.5);
    }

    @Override
    public void swim(int distance) {
        printResultActions("swim", distance,distance <= limitSwim, limitSwim);
    }
}
