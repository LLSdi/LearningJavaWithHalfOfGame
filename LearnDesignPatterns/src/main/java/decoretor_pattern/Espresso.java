package decoretor_pattern;

public class Espresso extends Beverage {

    public Espresso() {
        decription = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
