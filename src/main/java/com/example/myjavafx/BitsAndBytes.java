package com.example.myjavafx;

import javafx.application.Application;
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

        // Test Order API
         ArrayList<Order> orderList = OrderAPI.getOrderInfo();
         Order newOrder1 = new Order();
        newOrder1.addToOrder(new CartItem(new Item("thin", "pepperoni", 12.57),2));
        newOrder1.addToOrder(new CartItem(new Item("thick", "cheese", 57.00),7));
       newOrder1.addToOrder(new CartItem(new Item("medium", "pineapple", 11.57),4));
        newOrder1.addToOrder(new CartItem(new Item("thin", "mushroom", 0.57),3));
        newOrder1.addToOrder(new CartItem(new Item("thin", "chicken", 0.07),1));
        orderList.add(newOrder1);

        Order newOrder2 = new Order();
        newOrder2.addToOrder(new CartItem(new Item("thin2", "pepperoni2", 12.57),2));
        newOrder2.addToOrder(new CartItem(new Item("thick2", "cheese2", 57.00),7));
        newOrder2.addToOrder(new CartItem(new Item("medium2", "pineapple2", 11.57),4));
        newOrder2.addToOrder(new CartItem(new Item("thin2", "mushroom2", 0.57),3));
        newOrder2.addToOrder(new CartItem(new Item("thin2", "chicken2", 0.07),1));
        orderList.add(newOrder2);
        OrderAPI.saveData(OrderAPI.getOrderInfo());
    }

    public static void main(String[] args) {
        launch();
    }
}