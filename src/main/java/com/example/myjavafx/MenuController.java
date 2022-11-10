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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    @FXML
    private Label loginError;
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
    private TextField asuID;
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

    public void SignUpSaved(ActionEvent event) throws IOException {
        if(firstName.getText().isEmpty()){ signUpError.setText("Please enter First Name!"); }
        else if(lastName.getText().isEmpty()){ signUpError.setText("Please Enter Last Name!"); }
        else if(asuID.getText().isEmpty()){ signUpError.setText("Please Enter ASU ID!"); }
        else if(asuEmail.getText().isEmpty()){ signUpError.setText("Please Enter ASU Email!"); }
        else if(password.getText().isEmpty()){ signUpError.setText("Please Enter ASU Password!"); }
        else {
            String fileName = "Database/users.txt";
            Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
            String data = scanner.useDelimiter("\\A").next();
            scanner.close();
            List<String> ids = Arrays.stream(data.split("\n")).map(line -> line.split(",")[0].trim()).collect(Collectors.toList());
            List<String> emails = Arrays.stream(data.split("\n")).map(line -> line.split(",")[1].trim()).collect(Collectors.toList());
            List<String> pass = Arrays.stream(data.split("\n")).map(line -> line.split(",")[2].trim()).collect(Collectors.toList());

            for (int i = 0; i < ids.size(); i++) {
                if (ids.contains(asuID.getText())) { signUpError.setText("ASU ID already in use, please try again."); asuID.clear();}
                else if(emails.contains(asuEmail.getText())){ signUpError.setText("ASU EMAIL already in use, please try again."); asuEmail.clear();}
                else if(pass.contains(password.getText())){ signUpError.setText("ASU PASSWORD already in use, please try again."); password.clear();}
                else
                {
                    String text = asuID.getText() + "," +
                            asuEmail.getText() + "," +
                            password.getText() + "," +
                            firstName.getText() + "," +
                            lastName.getText();
                    BufferedWriter buf = new BufferedWriter(new FileWriter(fileName, true));
                    buf.write(text + "\n");
                    buf.close();
                    signUpError.setText("You have successfully signed up!");

                    firstName.clear(); lastName.clear(); asuID.clear(); asuEmail.clear(); password.clear();
                    //***possible add, return user to the login page
                }
                break;
            }
        }
    }

    public void LoginHandler(ActionEvent event) throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
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
    }
}


