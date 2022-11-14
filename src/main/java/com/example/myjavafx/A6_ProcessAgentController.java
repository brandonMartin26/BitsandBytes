package com.example.myjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class A6_ProcessAgentController implements Initializable {
    @FXML
    private ListView processAgentListView;

    @FXML
    private Button acceptOrderButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Order selectedOrder;
    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    ObservableList<Order> orders = FXCollections.observableArrayList();
    ObservableList<String> names = FXCollections.observableArrayList();

    public void switchToChefView(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b7_ChefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProcessing(ActionEvent event) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("processingPage.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        names.clear();
        for(Order order : orderList) {
            orders.add(order);
            names.add(order.toString());
        }
        processAgentListView.setItems(names);
        processAgentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    public void processingAgentAccept(ActionEvent event) throws IOException {
        selectedOrder = (Order)processAgentListView.getSelectionModel().getSelectedItem();
        selectedOrder.status = 1;
        names.remove(processAgentListView.getSelectionModel().getSelectedIndex());
    }
    public void processingViewOrder(ActionEvent event) throws IOException {
        //processingTextArea.clear();
        Boolean moreOrders = true;
        /*String tempOrder = newOrder.toString();
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
        }*/
    }
}