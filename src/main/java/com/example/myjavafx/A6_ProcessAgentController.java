package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class A6_ProcessAgentController {
    //FXML Variables
    @FXML private Button processLoadOrdersBtn, acceptOrderButton, chefBtnPA,orderUpdateBtnPA;
    @FXML private Label orderToProcessLabel;
    @FXML private TextArea processingTextArea;
    //Scene Variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Order Variables
    static Order newOrder = new Order();
    ArrayList<Order> orderList = OrderAPI.getOrderInfo();

    public void switchToChefView(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b7_ChefView.fxml"));
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
    public void processingAgentAccept(ActionEvent event) throws IOException {
        orderList.get(0).status = 1;
        processingTextArea.clear();
    }
    public void processingViewOrder(ActionEvent event) throws IOException {
        processingTextArea.clear();
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
            processingTextArea.setText("" + pizzaType + "\n\t" + toppings + "\n");
        }
    }
}
