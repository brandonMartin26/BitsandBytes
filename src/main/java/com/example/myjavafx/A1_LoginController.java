
package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class A1_LoginController {
    @FXML private AnchorPane loginAnchor;
    @FXML private Button loginBtn, signUpBtn, contAsGuestBtn;
    @FXML private TextField usernameField, passwordField;
    @FXML private Label loginLabel, usernameLabel, passwordLabel, loginError;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSignUp(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b4_SignupView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMenu(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("b2_MenuView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void LoginHandler(ActionEvent event) throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
        {
            loginError.setText("Please make sure all fields are full!");
            return;
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
                    switchToMenu(event);
                } else {
                    loginError.setText("Invalid username or password");
                    return;
                }
            }
            scanner.nextLine();
        }
        loginError.setText("Invalid username or password");
    }
}
