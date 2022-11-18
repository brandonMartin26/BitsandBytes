package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class A5_ProcessUpdateController implements Initializable {

    @FXML private Label yourOrderIsLabel, acceptedLabel, readyToCookLabel, cookingLabel, readyLabel;
    @FXML private Button refreshOrder, chefViewTempBtn, processAgentOrderUpdateBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
