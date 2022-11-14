package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class A7_ChefController {
    // FXML Variables
    @FXML private AnchorPane chefViewAnchor;
    @FXML private ScrollPane chefScrollPane;
    @FXML private VBox displayOrder;
    @FXML private Button startOrderBtn, completeOrderBtn, orderUpdateBtnC, processAgentBtnC;
    @FXML private Label chefLabel;
    // Scene Variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Order Variables
    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();


    public void switchToProcessingAgent(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b6_ProcessAgentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProcessing(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b5_ProcessUpdateView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void startOrderHandler(ActionEvent event) throws IOException {
        orderList.get(0).status = 2;
    }
    public void completeOrderHandler(ActionEvent event) throws IOException {
        orderList.get(0).status = 3;
    }

    public void updateChefView(ActionEvent event) throws IOException {
        Boolean moreOrders = true;
        String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0, iend);
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
            displayOrder.getChildren().add(orderLabel);
        }
    }

}
