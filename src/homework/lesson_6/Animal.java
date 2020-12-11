package homework.lesson_6;

public abstract class Animal {
    protected String name;
    protected double limitRun;
    protected double limitJump;

    public void run(double distance) {
        printResultActions("run", distance,distance <= limitRun, limitRun);
    }

    public void jump(double height) {
        printResultActions("jump", height,height <= limitJump, limitJump);
    }

    public Animal(String name, double confinesRun, double confinesJump) {
        this.name = name;
        this.limitRun = confinesRun;
        this.limitJump = confinesJump;
    }

    protected void printResultActions(String action, double actionValue, boolean result, double limit) {
        System.out.println(String.format("%s.%s(%s) => %s " + (!result ? "limit(%s)" : ""), name, action, actionValue, result, limit));
    }
}
