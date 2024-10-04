package inheritant;

public class Main1 {
    public static void main(String[] args) {
        VNPerson vnPerson = new VNPerson();

        String weather = new String("rain");
        vnPerson.runWithContext(weather);

    }
}
