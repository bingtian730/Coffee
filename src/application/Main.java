package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    // Main UI components
    private ComboBox<String> coffeeTypeComboBox;
    private ComboBox<String> sizeComboBox;
    private ComboBox<String> milkComboBox;
    private Button orderButton;

    public static void main(String[] args) {
        launch(args);  // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Step 1: Set the title for the main window (Stage)
        primaryStage.setTitle("Coffee Order App");

        // Step 2: Define UI components
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
        orderButton.setOnAction(e -> placeOrder());

        // Step 3: Layout setup (VBox is a layout that arranges components vertically)
        VBox layout = new VBox(10);  // Vertical box with 10px spacing between items
        layout.setPadding(new javafx.geometry.Insets(20));  // Add padding around the VBox
        layout.getChildren().addAll(
                new Label("Select Coffee Type:"), coffeeTypeComboBox,
                new Label("Select Size:"), sizeComboBox,
                new Label("Select Milk Type:"), milkComboBox,
                orderButton
        );

        // Step 4: Create a Scene using the layout (VBox)
        Scene scene = new Scene(layout, 300, 250);  // Width: 300px, Height: 250px

        // Step 5: Set the Scene on the primary Stage (window)
        primaryStage.setScene(scene);

        // Step 6: Show the window (Stage)
        primaryStage.show();
    }

    private void placeOrder() {
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
            // Prepare the order details and price
            String orderDetails = coffee.prepare();
            double price = coffee.calculatePrice();
            String orderSummary = orderDetails + "\nTotal Price: $" + String.format("%.2f", price);

            // Create a new window (Stage) for the order summary
            Stage summaryStage = new Stage();
            summaryStage.setTitle("Order Summary");

            // Create a Label to show the order summary
            Label summaryLabel = new Label(orderSummary);
            
            // Set up layout for the new window
            VBox summaryLayout = new VBox(10);
            summaryLayout.setPadding(new javafx.geometry.Insets(20));
            summaryLayout.getChildren().addAll(summaryLabel);

            // Create and set the Scene for the new window
            Scene summaryScene = new Scene(summaryLayout, 300, 200);
            summaryStage.setScene(summaryScene);

            // Show the new window
            summaryStage.show();
        } else {
            // Handle the error if the coffee type is invalid (shouldn't happen in this case)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid coffee type");
            alert.setContentText("Please select a valid coffee type.");
            alert.showAndWait();
        }
    }
}
