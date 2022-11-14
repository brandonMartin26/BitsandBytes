
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class A3_CheckoutController {
    @FXML private GridPane asuIDPane;
    @FXML private VBox checkoutCartVbox;
    @FXML private TextField asuIdField;
    @FXML private Label asuIdLabel;
    @FXML private Button backToMenuBtn;
    @FXML private AnchorPane checkoutAnchor;
    @FXML private GridPane checkoutCartGridPane;
    @FXML private Label checkoutCartLabel;
    @FXML private ScrollPane checkoutCartScroll;
    @FXML private Label checkoutLabel;
    @FXML private Button confirmOrderBtn;
    @FXML private Label totalLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();



    public void switchToMenu(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("bitsandbytes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProcessing(ActionEvent event) throws IOException
    {
        orderList.add(newOrder);
        OrderAPI.saveData(orderList);
        root = FXMLLoader.load(getClass().getResource("processingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void populateFinalCheckoutCart() throws IOException {
       String fileName = "Database/oderDB.txt";
       Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
       String data = scanner.useDelimiter("\\A").next();
       scanner.close();




    }
}
