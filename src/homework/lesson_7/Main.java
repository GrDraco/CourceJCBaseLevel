package homework.lesson_7;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("---У нас появились коята---");
        EatingCat[] cats = {
                new EatingCat("cat_1",new Random().nextInt(11) + 20),
                new EatingCat("cat_2",new Random().nextInt(11) + 20),
                new EatingCat("cat_3",new Random().nextInt(11) + 20),
                new EatingCat("cat_4",new Random().nextInt(11) + 20),
                new EatingCat("cat_5",new Random().nextInt(11) + 20)
        };
        for (EatingCat cat: cats)
            System.out.println(cat.toString());
        System.out.println("---Котята кушают---");
        AnimalBowl bowl = new AnimalBowl(new Random().nextInt(21) + 80);
        System.out.println(bowl.toString());
        bowl.feedAnimals(cats);
        System.out.println("---Опрос котят---");
        for (EatingCat cat: cats) {
            cat.printSatiety();
        }
    }
}
