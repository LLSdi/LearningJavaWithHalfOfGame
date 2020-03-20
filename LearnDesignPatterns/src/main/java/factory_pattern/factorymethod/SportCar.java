package factory_pattern.factorymethod;

public class SportCar implements Vehicle {
    @Override
    public void getInfo() {
        System.out.println("I'm a SportCar...");
    }
}
