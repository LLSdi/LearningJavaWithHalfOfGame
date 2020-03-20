package factory_pattern.simplefactory;

public class Truck implements Vehicle {
    @Override
    public void move() {
        System.out.println("Truck moving");
    }
}
