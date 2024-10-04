package inheritant;

public class FRPerson extends Person{
    public FRPerson() {}


    @Override
    void running() {
        System.out.println("FRPerson: Running ");
    }

    @Override
    void runningInRain() {
        System.out.println("FRPerson: Running in rain");
    }

    @Override
    void runningInSun() {
        System.out.println("FRPerson: Running in sun");
    }
}
