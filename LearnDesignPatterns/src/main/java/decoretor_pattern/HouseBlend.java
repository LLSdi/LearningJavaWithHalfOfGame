package decoretor_pattern;

public class HouseBlend extends Beverage{

    public HouseBlend() {
        decription = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
