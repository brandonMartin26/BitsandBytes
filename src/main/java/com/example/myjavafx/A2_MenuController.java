package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.PizzaType;
import com.example.myjavafx.core.models.enums.Topping;
import javafx.application.Platform;
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
import java.util.*;


import static com.example.myjavafx.core.models.PizzaRecord.*;
import static com.example.myjavafx.core.models.PizzaUtils.pizzaRecordsToOrderList;

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
    private List<PizzaRecord> pizzas = new ArrayList<>();

    private String orderId;
    private LoginPayload loginPayload;
    public void setLoginPayload(LoginPayload payload)
    {
        loginPayload = payload;
    }


    public void setOrder(CheckoutPayload payload) {
        this.orderId = payload.orderId;
        this.pizzas = payload.pizzas;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        api = new OrderApiImpl();
//        orderId = UUID.randomUUID().toString();
        Random randomId = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 1) {
            int random = randomId.nextInt(99)+100000;
            set.add(random);
        }
        int length = 6;
        String rand = String.valueOf(length);
        for(int random1 : set) {
            rand = Integer.toString(random1);
        }
        orderId = rand;
        Platform.runLater(() -> {

            for (String pizza : pizzaRecordsToOrderList(pizzas)) {
                Label checkoutCartLabel = new Label();
                checkoutCartLabel.wrapTextProperty().setValue(true);
                checkoutCartLabel.setMaxWidth(100);
                checkoutCartLabel.setText(pizza);
                cartVbox.getChildren().add(checkoutCartLabel);
            }
        });

        //List<String> toppings = new ArrayList<>();
        try {
            addToOrderBtn.setOnAction(e -> {

                ArrayList<Topping> toppings = new ArrayList<>();
                PizzaType pizzaType = PizzaType.PLAIN;

                if (pepRadio.isSelected() || cheeseRadio.isSelected() || vegRadio.isSelected()) {
                    if (pepRadio.isSelected()) {
                        pizzaType = PizzaType.PEPPERONI;
                    } else if (vegRadio.isSelected()) {
                        pizzaType = PizzaType.VEGETABLE;
                    } else {
                        pizzaType = PizzaType.CHEESE;
                    }
                    // Toppings
                    if (exCheeseCheckbox.isSelected()||mushCheckbox.isSelected()||olivesCheckbox.isSelected()||onionCheckbox.isSelected()) {
                        if (exCheeseCheckbox.isSelected()) {
                            toppings.add(Topping.EXTRA_CHEESE);
                        }
                        if (mushCheckbox.isSelected()) {
                            toppings.add(Topping.MUSHROOMS);
                        }
                        if (olivesCheckbox.isSelected()) {
                            toppings.add(Topping.OLIVES);
                        }
                        if (onionCheckbox.isSelected()) {
                            toppings.add(Topping.ONIONS);
                        }
                        Label orderLabel = new Label();
                        orderLabel.wrapTextProperty().setValue(true);
                        orderLabel.setMaxWidth(100);
                        String finalOrder = pizzaTypeToString(pizzaType);
                        for (Topping topping : toppings) {
                            finalOrder += "\n\t" + toppingToString(topping);
                        }
                        orderLabel.setText(finalOrder);
                        cartVbox.getChildren().add(orderLabel);
                        pizzas.add(new PizzaRecord(orderId, pizzaType, toppings));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No Toppings Selected");
                        alert.setContentText("Please select at least one topping.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No Pizza Selected");
                    alert.setContentText("Please select a pizza type");
                    alert.showAndWait();
                }
            });

            clearOrderBtn.setOnAction(e -> {
                cartVbox.getChildren().clear();
                pizzas.clear();
            });

            checkoutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        if(!cartVbox.getChildren().isEmpty()) {

                            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("b3_CheckoutView.fxml"));
                            Parent root = fxmlLoader.load();
                            A3_CheckoutController checkoutController = fxmlLoader.getController();
                            checkoutController.setCheckoutOrder(new CheckoutPayload(orderId, pizzas));
                            checkoutController.setLoginPayload(loginPayload);
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
}
