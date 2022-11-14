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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class A7_ChefController implements Initializable {
    // FXML Variables
    @FXML private AnchorPane chefViewAnchor;
    @FXML private ScrollPane chefScrollPane;
    @FXML private ListView chefListView;
    @FXML private Button startOrderBtn, completeOrderBtn, orderUpdateBtnC, processAgentBtnC;
    @FXML private Label chefLabel;
    // Scene Variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Order Variables
    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();
    ObservableList<Order> orders = FXCollections.observableArrayList();
    Order selectedOrder;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orders.clear();
        for(Order order : orderList) {
            orders.add(order);
        }
        chefListView.setItems(orders);
        chefListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

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
        selectedOrder = (Order)chefListView.getSelectionModel().getSelectedItem();
        if(selectedOrder == null) {
            return;
        }
        selectedOrder.status = 2;
    }
    public void completeOrderHandler(ActionEvent event) throws IOException {
        selectedOrder = (Order)chefListView.getSelectionModel().getSelectedItem();
        if(selectedOrder == null) {
            return;
        }
        selectedOrder.status = 3;
    }

}
