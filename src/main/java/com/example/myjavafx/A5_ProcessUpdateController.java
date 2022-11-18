package com.example.myjavafx;

import com.example.myjavafx.core.models.OrderRecord;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;

public class A5_ProcessUpdateController implements Initializable {

    @FXML private Label yourOrderIsLabel, acceptedLabel, readyToCookLabel, cookingLabel, readyLabel;
    @FXML private Button refreshOrder, chefViewTempBtn, processAgentOrderUpdateBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    OrderApi api;
    List<OrderRecord> orderRecords;
    private CheckoutPayload checkoutOrder;
    public void setCheckoutOrder(CheckoutPayload payload) {
        this.checkoutOrder = payload;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // color change "setupUpdate()" where
        // updates periodically with a timer
        // check if status is changed, if so, change color to that label
        setupUpdater();
        chefViewTempBtn.setOnAction(event -> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b7_ChefView.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        processAgentOrderUpdateBtn.setOnAction(event -> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("b6_ProcessAgentView.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
