package com.example.myjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BitsAndBytes extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bitsandbytes.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        OrderAPI.saveData(OrderAPI.getOrderInfo());
    }


    public static void main(String[] args) {
        launch();
    }
}