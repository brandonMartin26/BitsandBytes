
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
    //FXML Variables
    @FXML
    private AnchorPane checkoutAnchor;
    @FXML
    private GridPane checkoutCartGridPane, asuIDPane;

    @FXML
    private ScrollPane checkoutCartScroll;
    @FXML
    private VBox checkoutCartVbox;
    @FXML
    private TextField asuIdField;
    @FXML
    private Label asuIdLabel, checkoutCartLabel, checkoutLabel, totalLabel;
    @FXML
    private Button backToMenuBtn, confirmOrderBtn;

    // Setting Variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Order Variables
    static ArrayList<Order> orderList = OrderAPI.getOrderInfo();
    static Order newOrder = new Order();
    private ActionEvent event;
    private String confirmedID;


    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b2_MenuView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChefView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b7_ChefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOrderProcessAgentView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b6_ProcessAgentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProcessing(ActionEvent event) throws IOException {
        this.event = event;
        //TODO: Make sure confirmID checks the ID against the same username used to login.
        if (confirmID()) {
            /*if(confirmedID.equals("111111111") || confirmedID.equals("222222222") || confirmedID.equals("333333333") || confirmedID.equals("444444444") || confirmedID.equals("555555555")) {
                switchToChefView(event);
            } else if(confirmedID.equals("666666666") || confirmedID.equals("777777777") || confirmedID.equals("888888888")) {
                switchToOrderProcessAgentView(event);
            } else {*/
            orderList.add(newOrder);
            OrderAPI.saveData(orderList);
            root = FXMLLoader.load(getClass().getResource("b5_ProcessUpdateView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            asuIdField.setText("Invalid ASU ID!");
        }
    }

    public void populateFinalCheckoutCart() throws IOException {
       String fileName = "Database/oderDB.txt";
       Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
       String data = scanner.useDelimiter("\\A").next();
       scanner.close();
    }

    public boolean confirmID() throws IOException{
        String fileName = "Database/users.txt";
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        scanner.useDelimiter(",");
        String asuID = asuIdField.getText();
        String fileID;
        while(scanner.hasNextLine())
        {
            fileID = scanner.next();
            if(asuID.equals(fileID)) //can change to fileID to login with ID instead of email
            {
                confirmedID = asuID;
                return true;
            } else {
                scanner.nextLine();
            }
        }
        return false;
    }
}
