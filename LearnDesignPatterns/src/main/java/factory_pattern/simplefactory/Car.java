package factory_pattern.simplefactory;

public class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Car moving...");
    }
}
