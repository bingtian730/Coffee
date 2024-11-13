package application;

public abstract class CoffeeDrink {
    protected String size;
    protected String milk;
    
    // Constructor
    public CoffeeDrink(String size, String milk) {
        this.size = size;
        this.milk = milk;
    }

    // Abstract method for preparing the coffee
    public abstract String prepare();
    
    // Method to display order details
    public String getDetails() {
        return "Size: " + size + ", Milk: " + milk;
    }
}
