package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class A5_ProcessUpdateController {
    @FXML private Label yourOrderIsLabel;
    @FXML private Label acceptedLabel;
    @FXML private Label readyToCookLabel;
    @FXML private Label cookingLabel;
    @FXML private Label readyLabel;
    ArrayList<Order> orderList = OrderAPI.getOrderInfo();

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToChefView(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b7_ChefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProcessingAgent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b6_ProcessAgentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshStatus(ActionEvent event) throws InterruptedException {
        int temp = orderList.get(0).status;
        System.out.println(orderList.get(0).status);
        if(temp == 1){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            //Thread.sleep(5000);
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 2){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 3){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
            readyLabel.setStyle("-fx-background-color: #3E872B;");
        }
    }


}
