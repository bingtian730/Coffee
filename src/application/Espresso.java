package application;

public class Espresso extends CoffeeDrink {
    public Espresso(String size, String milk) {
        super(size, milk);
    }
    
    @Override
    public String prepare() {
        return "Making an Espresso with " + getDetails();
    }
}
