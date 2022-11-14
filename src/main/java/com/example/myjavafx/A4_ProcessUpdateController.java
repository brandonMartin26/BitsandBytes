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

public class A4_ProcessUpdateController {
    @FXML private Label yourOrderIsLabel;
    @FXML private Label acceptedLabel;
    @FXML private Label readyToCookLabel;
    @FXML private Label cookingLabel;
    @FXML private Label readyLabel;
    @FXML private Button Label1;
    @FXML private Button Label2;
    @FXML private Button Label3;
    @FXML private Button Label4;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToChefView(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("chefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void makeLabel1Green(ActionEvent event) throws IOException {
        acceptedLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel2Green(ActionEvent event) throws IOException {
        readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel3Green(ActionEvent event) throws IOException {
        cookingLabel.setStyle("-fx-background-color: #3E872B;");
    }
    public void makeLabel4Green(ActionEvent event) throws IOException {
        readyLabel.setStyle("-fx-background-color: #3E872B;");
    }
}
