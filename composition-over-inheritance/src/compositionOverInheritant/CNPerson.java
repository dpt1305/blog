package compositionOverInheritant;

public class CNPerson {

    private PersonBehavior behaviors;

    public CNPerson(PersonBehavior runBehavior) {
        this.behaviors = runBehavior;
    }

    public void runningWithContext() {
        this.behaviors.run();
    }
}
