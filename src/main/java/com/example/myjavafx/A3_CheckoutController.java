
package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import com.example.myjavafx.A2_MenuController;

import java.net.URL;
import java.util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class A3_CheckoutController implements Initializable {
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
    private ActionEvent event;
    private String confirmedID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo update vbox with orders
        //A2_MenuController controller = FXMLLoader(getClass().getResource("b2_MenuView.fxml"));
        A2_MenuController a2controller;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("b2_MenuView.fxml"));
            root = loader.load();
            a2controller = loader.getController();
            newOrders = a2controller.getNewOrders();
            for(String order : newOrders)
            {
                Label checkoutCartLabel = new Label();
                checkoutCartLabel.wrapTextProperty().setValue(true);
                checkoutCartLabel.setMaxWidth(100);
                checkoutCartLabel.setText(order);
                System.out.println(order);
                checkoutCartVbox.getChildren().add(checkoutCartLabel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            backToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b2_MenuView.fxml")));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            /*confirmOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
                @                   if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
        {
            loginError.setText("Please make sure all fields are full!");
        }
        String fileName = "Database/users.txt";
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        scanner.useDelimiter(",");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fileID;
        String fileEmail;
        String filePassword;

        while(scanner.hasNextLine())
        {
            fileID = scanner.next();
            fileEmail = scanner.next();
            filePassword = scanner.next();
            loginError.setText(fileEmail);

            if(username.equals(fileEmail)) //can change to fileID to login with ID instead of email
            {
                if(password.equals(filePassword))
                {
                    //can pass username to elsewhere here, if needed for other parts
                    switchToCheckout(event);

                } else {
                    loginError.setText("Invalid username or password");
                    break;
                }
            }
            scanner.nextLine();
        }
        loginError.setText("Invalid username or password");
                public void handle(ActionEvent event) {
                    try {
                    1.check if login matches database
                    2.change to process update view fxml. if not --> error

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });*/
    }
}
