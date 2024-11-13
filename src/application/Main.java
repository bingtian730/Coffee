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
    private Label orderDetailsLabel;

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

        orderDetailsLabel = new Label();

        // Step 3: Layout setup (VBox is a layout that arranges components vertically)
        VBox layout = new VBox(10);  // Vertical box with 10px spacing between items
        layout.setPadding(new javafx.geometry.Insets(20));  // Add padding around the VBox
        layout.getChildren().addAll(
                new Label("Select Coffee Type:"), coffeeTypeComboBox,
                new Label("Select Size:"), sizeComboBox,
                new Label("Select Milk Type:"), milkComboBox,
                orderButton, orderDetailsLabel
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
        
        // Display order details
        if (coffee != null) {
            orderDetailsLabel.setText("Your order: " + coffee.prepare());
        } else {
            orderDetailsLabel.setText("Error: Invalid coffee type.");
        }
    }
}
