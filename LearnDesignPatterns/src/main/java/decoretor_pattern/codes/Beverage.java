package decoretor_pattern.codes;

public abstract class Beverage {
    String decription = "Unknown Beverage";

    public String getDecription() {
        return decription;
    }

    public abstract double cost();
}
