package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class A1_MenuController {
    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();
    @FXML private Button addToOrderBtn, clearOrderBtn, checkoutBtn;
    @FXML private RadioButton cheeseRadio, pepRadio, vegRadio;
    @FXML private CheckBox exCheeseCheckbox, mushCheckbox, olivesCheckbox, onionCheckbox;
    @FXML private ToggleGroup pizzaTypes;
    @FXML private VBox cartVbox;
    @FXML private GridPane cartGridPane, menuGridPane, menuItemsGridPane;
    @FXML private Label cartLabel, menuLabel, topLabel;
    @FXML private ScrollPane cartScroll;
    @FXML private AnchorPane menuAnchor;
    @FXML private TextField searchField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMemberLogin(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("memberLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addToOrder(ActionEvent event) throws IOException
    {
        String toppings = " ";
        String type = " ";
        double price = 0.00;
        if(pepRadio.isSelected()){
            type = "Pepperoni";
            price += 10.00;
        }
        else if(vegRadio.isSelected()){
            type = "Vegetable";
            price += 12.00;
        }
        else if(cheeseRadio.isSelected()){
            type = "Cheese";
            price += 9.00;
        }
        if(mushCheckbox.isSelected()){
            toppings += "Mushroom^";
            price += 1.50;
        }
        if(onionCheckbox.isSelected()){
            toppings += "Onion^";
            price += 1.50;
        }
        if(olivesCheckbox.isSelected()){
            toppings += "Olives^";
            price += 1.50;
        }
        if(exCheeseCheckbox.isSelected()){
            toppings += "ExtraCheese^";
            price += 1.50;
        }
        //toppings = toppings.substring(0,(toppings.length()));
        Item newItem = new Item(type, toppings, price);
        newItem.setPizzaType(type);
        newOrder.addToOrder(new CartItem(newItem, 1));
        updateCart(newItem);
    }

    public void updateCart(Item newItem)
    { populateCheckoutCart(); }

    public void populateCheckoutCart()
    {
        cartVbox.getChildren().clear();
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0,iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            orderLabel.setText("" + pizzaType + "\n\t" + toppings + "\n");
            cartVbox.getChildren().add(orderLabel);

        }

    }
    public  void clearVBoxCart(ActionEvent event) throws IOException
    {
        newOrder.clearOrder();
        cartVbox.getChildren().clear();
    }
}
