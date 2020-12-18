package homework.lesson_7;

import homework.lesson_6.Animal;

import java.util.Random;

public class AnimalBowl {

    protected int food;

    public AnimalBowl(int food) {
        this.food = food > 0 ? food : 0;
    }

    public void feedAnimals(EatingCat[] cats) {
        for (EatingCat cat: cats) {
            if (food <= 0)
                return;
            food -= cat.eat(food);
        }
    }

    @Override
    public String toString() {
        return String.format("Food = %s", food);
    }
}
