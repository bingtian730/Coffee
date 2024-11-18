package application;

public class Latte extends CoffeeDrink {
    public Latte(String size, String milk) {
        super(size, milk);
    }

    @Override
    public String prepare() {
        return "Making a Latte with " + getDetails();
    }
    
    @Override
    public double calculatePrice() {
        double price = LATTE_BASE_PRICE;

        switch (size) {
            case "Medium":
                price += MEDIUM_SIZE_INCREMENT;
                break;
            case "Large":
                price += LARGE_SIZE_INCREMENT;
                break;
            case "Small": // no change, base price used
                break;
        }

        return price;
    }
}
