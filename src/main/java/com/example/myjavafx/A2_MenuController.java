package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.PizzaType;
import com.example.myjavafx.core.models.enums.Topping;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;

import static com.example.myjavafx.core.models.PizzaRecord.*;

public class A2_MenuController implements Initializable {
    @FXML
    private Button addToOrderBtn, clearOrderBtn, checkoutBtn;
    @FXML
    private RadioButton cheeseRadio, pepRadio, vegRadio;
    @FXML
    private CheckBox exCheeseCheckbox, mushCheckbox, olivesCheckbox, onionCheckbox;
    @FXML
    private ToggleGroup pizzaTypes;
    @FXML
    private VBox cartVbox;
    @FXML
    private GridPane cartGridPane, menuGridPane, menuItemsGridPane;
    @FXML
    private Label cartLabel, menuLabel, topLabel;
    @FXML
    private ScrollPane cartScroll;
    @FXML
    private AnchorPane menuAnchor;
    @FXML
    private TextField searchField;

    ActionEvent event;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private OrderApi api;
    private List<String> toppings = new ArrayList<>();
    PizzaType pizzaType;
    private List<String> newOrders = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        api = new OrderApiImpl();
        //List<String> toppings = new ArrayList<>();
        try {
            addToOrderBtn.setOnAction(e -> {
                if(pepRadio.isSelected() || cheeseRadio.isSelected() || vegRadio.isSelected()) {
                    if (pepRadio.isSelected()) {
                        pizzaType = PizzaType.PEPPERONI;
                    } else if (vegRadio.isSelected()) {
                        pizzaType = PizzaType.VEGETABLE;
                    } else {
                        pizzaType = PizzaType.CHEESE;
                    }
                    // Toppings
                    if(exCheeseCheckbox.isSelected()||mushCheckbox.isSelected()||olivesCheckbox.isSelected()||onionCheckbox.isSelected()) {
                        if (exCheeseCheckbox.isSelected()) {
                            toppings.add(toppingToString(Topping.EXTRA_CHEESE));
                        }
                        if (mushCheckbox.isSelected()) {
                            toppings.add(toppingToString(Topping.MUSHROOMS));
                        }
                        if (olivesCheckbox.isSelected()) {
                            toppings.add(toppingToString(Topping.OLIVES));
                        }
                        if (onionCheckbox.isSelected()) {
                            toppings.add(toppingToString(Topping.ONIONS));
                        }
                        Label orderLabel = new Label();
                        orderLabel.wrapTextProperty().setValue(true);
                        orderLabel.setMaxWidth(100);
                        String finalOrder = pizzaTypeToString(pizzaType);
                        for (String topping : toppings) {
                            finalOrder += "\n\t" + topping;
                        }
                        orderLabel.setText(finalOrder);
                        addNewOrder();
                        cartVbox.getChildren().add(orderLabel);
                        toppings.clear();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No Toppings Selected");
                        alert.setContentText("Please select at least one topping.");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No Pizza Selected");
                    alert.setContentText("Please select a pizza type");
                    alert.showAndWait();
                }
            });
            clearOrderBtn.setOnAction(e -> {
                cartVbox.getChildren().clear();
                newOrders.clear();
            });
            checkoutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        if(!cartVbox.getChildren().isEmpty()) {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b3_CheckoutView.fxml")));
                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("No Pizzas In Cart");
                            alert.setContentText("Please select a Pizza for Purchase");
                            alert.showAndWait();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addNewOrder()
    {
        String newOrder = "0000;" + pizzaType.toString() + ";";
        for(String topping : toppings)
        {
            newOrder += topping + ",";
        }
        newOrders.add(newOrder); //0000;Vegetable;Extra-Cheese,
        System.out.println(newOrders.get(0));

    }

    public List<String> getNewOrders()
    {
        return newOrders;
    }
}
