package homework.lesson_7;

import homework.lesson_6.Cat;

public class EatingCat extends Cat {

    protected int appetite;
    protected boolean satiety;

    public EatingCat(String name, int appetite) {
        super(name);
        this.appetite = appetite > 0 ? appetite : 0;
        satiety = false;
    }

    public boolean сanEat(int food) {
        return appetite > 0 && (food - appetite) >= 0;
    }

    public int eat(int food) {
        if (сanEat(food)) {
            satiety = true;
            System.out.printf("%s: ммм ням ням, я наелся %n", name);
            return appetite;
        }
        System.out.printf("%s: я отказываюсь есть %n", name);
        return 0;
    }

    public void printSatiety() {
        System.out.printf("%s: %s %n", name, satiety ? "я сытый мяу мяу :)" : "я голодный ррр :(");
    }

    @Override
    public String toString() {
        return String.format("%s (appetite = %s)", name, appetite);
    }
}
