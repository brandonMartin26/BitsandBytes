package com.example.myjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main_bnb extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b7_ChefView.fxml")));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b2_MenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //OrderAPI.saveData(OrderAPI.getOrderInfo());
    }


    public static void main(String[] args) {
        launch();
    }
}