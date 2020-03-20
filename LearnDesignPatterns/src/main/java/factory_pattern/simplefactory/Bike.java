package factory_pattern.simplefactory;

public class Bike implements Vehicle {
    @Override
    public void move() {
        System.out.println("Bike moving...");
    }
}
