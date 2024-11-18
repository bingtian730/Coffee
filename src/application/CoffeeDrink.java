package application;

public abstract class CoffeeDrink {
    protected String size;
    protected String milk;
    
 // Base prices for each coffee type (could be added as constants)
    public static final double ESPRESSO_BASE_PRICE = 2.50;
    public static final double LATTE_BASE_PRICE = 3.00;
    public static final double CAPPUCCINO_BASE_PRICE = 3.50;

 // Consistent price increases for size
    public static final double MEDIUM_SIZE_INCREMENT = 0.50;
    public static final double LARGE_SIZE_INCREMENT = 1.00;
    
    // Constructor
    public CoffeeDrink(String size, String milk) {
        this.size = size;
        this.milk = milk;
    }

    // Abstract method for preparing the coffee
    public abstract String prepare();
    
 // Abstract method to calculate the price based on size
    public abstract double calculatePrice();

    // Method to display order details
    public String getDetails() {
        return "Size: " + size + ", Milk: " + milk;
    }
    
    
}
