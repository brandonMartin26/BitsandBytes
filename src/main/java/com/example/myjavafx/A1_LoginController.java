
package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class A1_LoginController {
    @FXML
    private AnchorPane loginAnchor;
    @FXML
    private Button loginBtn, signUpBtn, contAsGuestBtn;
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label loginLabel, usernameLabel, passwordLabel, loginError;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b4_SignupView.fxml"));
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

    public void switchToProcessAgentView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b6_ProcessAgentView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("b2_MenuView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void LoginHandler(ActionEvent event) throws IOException {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            loginError.setText("Please make sure all fields are full!");
            return;
        }
        String fileName = "Database/users.txt";
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8);
        scanner.useDelimiter(",");
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fileID, fileEmail, filePassword, fileFirstName, fileLastName;
        while (scanner.hasNext()) {
            fileID = scanner.next();
            fileEmail = scanner.next();
            filePassword = scanner.next();
            scanner.nextLine();


            if(username.equals(fileEmail) && password.equals(filePassword)) {
                if(username.equals("chef1@asu.edu") && password.equals("chef1") || username.equals("chef2@asu.edu") && password.equals("chef2") || username.equals("chef3@asu.edu") && password.equals("chef3") || username.equals("chef4@asu.edu") && password.equals("chef4") || username.equals("chef5@asu.edu") && password.equals("chef5")) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b7_ChefView.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else if(username.equals("agent1@asu.edu") && password.equals("agent1") || username.equals("agent2@asu.edu") && password.equals("agent2") || username.equals("agent3@asu.edu") && password.equals("agent3")) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b6_ProcessAgentView.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else{
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b2_MenuView.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }


            }else{
                loginError.setText("Username or password is incorrect!");
            }


        }
    }
}

