package application;

public class Latte extends CoffeeDrink {
    public Latte(String size, String milk) {
        super(size, milk);
    }

    @Override
    public String prepare() {
        return "Making a Latte with " + getDetails();
    }
}
