package inheritant;

public class VNPerson extends Person {
    public VNPerson() {}

    @Override
    void running() {
        System.out.println("VNPerson: Running");
    }

    @Override
    void runningInRain() {
        System.out.println("VNPerson: Running in rain");
    }

    @Override
    void runningInSun() {
        System.out.println("VNPerson: Running in sun");
    }

    void runWithContext(String weather) {
        if(weather.equalsIgnoreCase("rain")) {
            runningInRain();
        } else if(weather.equalsIgnoreCase("sun")) {
            runningInSun();
        } else {
            running();
        }
    }
}
