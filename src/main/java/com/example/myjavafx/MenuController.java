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
import javafx.stage.Stage;

import java.io.*;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //BEGIN VARIABLES FOR MENU
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private GridPane cartGridPane;

    @FXML
    private Label cartLabel;

    @FXML
    private ScrollPane cartScroll;

    @FXML
    public Button checkoutBtn;

    @FXML
    private AnchorPane menuAnchor;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private GridPane menuItemsGridPane;

    @FXML
    private Label menuLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Label topLabel;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR MENU

    //BEGIN VARIABLES FOR LOGIN
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button contAsGuestBtn;

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button signUpBtn;
    @FXML
    private Label usernameLabel;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR LOGIN

    //START VARIABLES FOR SIGNUP
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button signUp;
    @FXML
    private Button backToLogin;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField asuEmail;
    @FXML
    private TextField password;
    @FXML
    private Label signUpError;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR SIGNUP

    //START VARIABLES FOR CHECKOUT
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private GridPane asuIDPane;

    @FXML
    private TextField asuIdField;

    @FXML
    private Label asuIdLabel;

    @FXML
    private Button backToMenuBtn;

    @FXML
    private AnchorPane checkoutAnchor;

    @FXML
    private GridPane checkoutCartGridPane;

    @FXML
    private Label checkoutCartLabel;

    @FXML
    private ScrollPane checkoutCartScroll;

    @FXML
    private Label checkoutLabel;

    @FXML
    private Button confirmOrderBtn;

    @FXML
    private Label totalLabel;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR CHECKOUT

    //START VARIABLES FOR PROCESSING
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Label cookingLabel;

    @FXML
    private Label readyLabel;

    @FXML
    private Label readyToCookLabel;

    @FXML
    private AnchorPane statusAnchor;

    @FXML
    private Label yourOrderIsLabel;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR PROCESSING

    //START VARIABLES FOR CHEF VIEW
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @FXML
    private Button backToMenuTempBtn;

    @FXML
    private AnchorPane chefViewAnchor;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //END VARIABLES FOR CHEF VIEW



    public void switchToMemberLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("memberLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bitsandbytes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCheckout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("checkoutPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProcessing(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("processingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChefView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chefView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUpSave(ActionEvent event) throws IOException{
        if(firstName.getText().isEmpty()){
            signUpError.setText("Please enter First Name!");
        }
        else if(lastName.getText().isEmpty()){
            signUpError.setText("Please Enter Last Name!");
        }
        else {
            String userFirstName = firstName.getText();
            String userLastName = lastName.getText();
            String userAsuEmail = asuEmail.getText();
            String userPassword = password.getText();
            String dir = "Database/";
            String fileName = userFirstName + "_" + userLastName + ".txt";
            String pathName = dir + fileName;
            System.out.println(fileName);
            File newUser = new File(dir, fileName);
            if (!newUser.exists()) {
                newUser.createNewFile();
                String text = userFirstName + "," +
                              userLastName + "," +
                              userAsuEmail + "," +
                              userPassword;
                BufferedWriter buf = new BufferedWriter(new FileWriter(pathName, true));
                buf.write(text);
                buf.close();


            } else {
                // test
                //test2
                signUpError.setText("User already exists, please try again!");
            }
        }
    }
}


