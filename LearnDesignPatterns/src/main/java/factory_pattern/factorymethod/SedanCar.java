package factory_pattern.factorymethod;

public class SedanCar implements Vehicle {
    @Override
    public void getInfo() {
        System.out.println("I'm a SedanCar...");
    }
}
