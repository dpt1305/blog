package compositionOverInheritant;

public class Main2 {
    public static void main(String[] args) {
        RunInRain runInRain = new RunInRain();
        CNPerson person = new CNPerson(runInRain);
        person.runningWithContext();
    }
}
