package application;

public class Espresso extends CoffeeDrink {
    public Espresso(String size, String milk) {
        super(size, milk);
    }
    
    @Override
    public String prepare() {
        return "Making an Espresso with " + getDetails();
    }
    
    @Override
    public double calculatePrice() {
        double price = ESPRESSO_BASE_PRICE;

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
