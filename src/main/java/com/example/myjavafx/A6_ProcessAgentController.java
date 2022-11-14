package com.example.myjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
public class A6_ProcessAgentController {
    @FXML
    private Label yourOrderIsLabel;
    @FXML
    private Label acceptedLabel;
    @FXML
    private Label readyToCookLabel;
    @FXML
    private Label cookingLabel;

    @FXML
    private Button processLoadOrdersButton;

    @FXML
    private Label readyLabel;

    @FXML
    private TextArea processingTextArea;

    @FXML
    private Button Label1;
    @FXML
    private Button Label2;
    @FXML
    private Button Label3;
    @FXML
    private Button Label4;
    @FXML
    private Button refreshOrder;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToChefView(ActionEvent event) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("chefView.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
    public void switchToProcessing(ActionEvent event) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("processingPage.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
    public void processingAgentAccept(ActionEvent event) throws IOException {
        //orderList.get(0).status = 1;
        processingTextArea.clear();
    }
    public void processingViewOrder(ActionEvent event) throws IOException {
        processingTextArea.clear();
        Boolean moreOrders = true;
        /*String tempOrder = newOrder.toString();
        while(moreOrders) {
            Label orderLabel = new Label();
            int iend = tempOrder.indexOf(";");
            String pizzaType = tempOrder.substring(0, iend);
            tempOrder = tempOrder.replaceFirst("" + pizzaType + ";", "");
            int iend2 = tempOrder.indexOf(";");
            String toppings = tempOrder.substring(0, iend2 + 1);
            toppings = toppings.replace("^", "\n\t");
            toppings = toppings.replace(";", "--------------------");
            tempOrder = tempOrder.replaceFirst("" + toppings + ";", "");
            int iend3 = tempOrder.indexOf("/");
            if(iend3 == -1){
                moreOrders = false;
                tempOrder = tempOrder.replace(tempOrder, "");
            }
            else{
                tempOrder = tempOrder.replace(tempOrder.substring(0,iend3+1), "");
            }
            processingTextArea.setText("" + pizzaType + "\n\t" + toppings + "\n");
        }*/
    }

    public void refreshStatus(ActionEvent event) throws InterruptedException {
        int temp = 1;//orderList.get(0).status;
        if(temp == 1){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            //Thread.sleep(5000);
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 2){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
        }
        if(temp == 3){
            acceptedLabel.setStyle("-fx-background-color: #3E872B;");
            readyToCookLabel.setStyle("-fx-background-color: #3E872B;");
            cookingLabel.setStyle("-fx-background-color: #3E872B;");
            readyLabel.setStyle("-fx-background-color: #3E872B;");
        }
    }
}
