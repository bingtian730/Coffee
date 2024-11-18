package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {

    // Main UI components
    private ComboBox<String> coffeeTypeComboBox;
    private ComboBox<String> sizeComboBox;
    private ComboBox<String> milkComboBox;
    private TextField customerNameTextField;  // TextField for customer name
    private Button orderButton;

    // List to store the orders (using Generics)
    private List<CoffeeDrink> coffeeOrders;

    public static void main(String[] args) {
        launch(args);  // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the list to store coffee orders
        coffeeOrders = new ArrayList<>();

        // Step 1: Set the title for the main window (Stage)
        primaryStage.setTitle("Coffee Order App");

        // Step 2: Define UI components
        customerNameTextField = new TextField();  // TextField to enter the customer name
        customerNameTextField.setPromptText("Enter your name");  // Set a prompt text in the field
        
        coffeeTypeComboBox = new ComboBox<>();
        coffeeTypeComboBox.getItems().addAll("Espresso", "Latte", "Cappuccino");
        coffeeTypeComboBox.getSelectionModel().selectFirst();

        sizeComboBox = new ComboBox<>();
        sizeComboBox.getItems().addAll("Small", "Medium", "Large");
        sizeComboBox.getSelectionModel().selectFirst();

        milkComboBox = new ComboBox<>();
        milkComboBox.getItems().addAll("Whole Milk", "Skim Milk", "Almond Milk", "Soy Milk");
        milkComboBox.getSelectionModel().selectFirst();

        orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> placeOrder(primaryStage));  // Pass the primaryStage to handle the order placement

        // Step 3: Layout setup (VBox is a layout that arranges components vertically)
        VBox layout = new VBox(10);  // Vertical box with 10px spacing between items
        layout.setPadding(new javafx.geometry.Insets(20));  // Add padding around the VBox

        // Add components to the layout
        layout.getChildren().addAll(
                new Label("Enter Your Name:"), customerNameTextField,
                new Label("Select Coffee Type:"), coffeeTypeComboBox,
                new Label("Select Size:"), sizeComboBox,
                new Label("Select Milk Type:"), milkComboBox,
                orderButton
        );

        // Step 4: Create a Scene using the layout (VBox)
        Scene scene = new Scene(layout, 300, 300);  // Adjust height for name input

        // Step 5: Set the Scene on the primary Stage (window)
        primaryStage.setScene(scene);

        // Step 6: Show the window (Stage)
        primaryStage.show();
    }

    private void placeOrder(Stage primaryStage) {
        // Get customer name from the TextField
        String customerName = customerNameTextField.getText().trim();
        
        // If the name field is empty, show an error and return
        if (customerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Customer Name");
            alert.setHeaderText("Please enter your name");
            alert.setContentText("You must provide your name before placing an order.");
            alert.showAndWait();
            return; // Don't proceed if the name is missing
        }

        // Get selected values from the combo boxes
        String coffeeType = coffeeTypeComboBox.getValue();
        String size = sizeComboBox.getValue();
        String milk = milkComboBox.getValue();
        
        // Create appropriate CoffeeDrink based on the selected type
        CoffeeDrink coffee = null;
        switch (coffeeType) {
            case "Espresso":
                coffee = new Espresso(size, milk);
                break;
            case "Latte":
                coffee = new Latte(size, milk);
                break;
            case "Cappuccino":
                coffee = new Cappuccino(size, milk);
                break;
        }
        
        // If the coffee is valid, create a new window to display the order summary
        if (coffee != null) {
            // Add the coffee order to the list (Generics used here)
            coffeeOrders.add(coffee);
            
            // Prepare the order details and price
            String orderDetails = coffee.prepare();
            double price = coffee.calculatePrice();
            String orderSummary = "Customer: " + customerName + "\n" + orderDetails + "\nTotal Price: $" + String.format("%.2f", price);

            // Create a new window (Stage) for the order summary
            Stage summaryStage = new Stage();
            summaryStage.setTitle("Order Summary");

            // Create a Label to show the order summary
            Label summaryLabel = new Label(orderSummary);
            
            // Create a "Confirm" button
            Button confirmButton = new Button("Confirm Order");
            confirmButton.setOnAction(e -> showThankYouPage(summaryStage));  // Handle the confirmation action

            // Create a "Make Changes" button
            Button makeChangesButton = new Button("Make Changes");
            makeChangesButton.setOnAction(e -> {
                summaryStage.close();  // Close the current order summary page
                primaryStage.show();   // Reopen the main order page
            });

            // Set up layout for the new window
            VBox summaryLayout = new VBox(10);
            summaryLayout.setPadding(new javafx.geometry.Insets(20));
            summaryLayout.getChildren().addAll(summaryLabel, confirmButton, makeChangesButton);

            // Create and set the Scene for the new window
            Scene summaryScene = new Scene(summaryLayout, 300, 200);
            summaryStage.setScene(summaryScene);

            // Show the new window
            summaryStage.show();

            // Hide the main page (order entry page) while the summary page is open
            primaryStage.hide();
        } else {
            // Handle the error if the coffee type is invalid (shouldn't happen in this case)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid coffee type");
            alert.setContentText("Please select a valid coffee type.");
            alert.showAndWait();
        }
    }

    // This method is called when the "Confirm" button is clicked
    private void showThankYouPage(Stage summaryStage) {
        // Close the order summary window
        summaryStage.close();

        // Create a new window for the "Thank You" message
        Stage thankYouStage = new Stage();
        thankYouStage.setTitle("Thank You");

        // Create the "Thank You" message
        Label thankYouLabel = new Label("Thank you for your order!\nWe will let you know when your coffee is ready.");

        // Set up layout for the thank you window
        VBox thankYouLayout = new VBox(20);
        thankYouLayout.setPadding(new javafx.geometry.Insets(20));
        thankYouLayout.getChildren().add(thankYouLabel);

        // Create and set the Scene for the thank you window
        Scene thankYouScene = new Scene(thankYouLayout, 300, 150);
        thankYouStage.setScene(thankYouScene);

        // Show the "Thank You" window
        thankYouStage.show();

        // Optionally, print all the orders using an iterator
        printAllOrders();
    }

    // Method to print all orders using an iterator
    private void printAllOrders() {
        Iterator<CoffeeDrink> iterator = coffeeOrders.iterator();
        while (iterator.hasNext()) {
            CoffeeDrink coffee = iterator.next();
            System.out.println(coffee.prepare() + " | Price: $" + coffee.calculatePrice());
        }
    }
}
