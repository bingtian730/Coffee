package application;

public class Cappuccino extends CoffeeDrink {
    public Cappuccino(String size, String milk) {
        super(size, milk);
    }

    @Override
    public String prepare() {
        return "Making a Cappuccino with " + getDetails();
    }
}

