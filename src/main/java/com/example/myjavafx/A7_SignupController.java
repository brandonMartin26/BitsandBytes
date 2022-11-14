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
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class A7_SignupController {
    @FXML private Button signUp;
    @FXML private Button backToLogin;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField asuID;
    @FXML private TextField asuEmail;
    @FXML private TextField password;
    @FXML private Label signUpError;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMemberLogin(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("memberLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signUpSave(ActionEvent event) throws IOException {
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
    }// END "signUpSave(ActionEvent event) throws IOException"
}
