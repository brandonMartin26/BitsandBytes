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
import javafx.scene.control.Alert;
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
    //********************** FXML Variables **********************\\
    @FXML private Label yourOrderIsLabel, acceptedLabel, readyToCookLabel, cookingLabel, readyLabel;
    @FXML private Button refreshOrder, chefViewTempBtn, processAgentOrderUpdateBtn;
    //********************** Setting Variables **********************\\
    private Stage stage;
    private Scene scene;
    private Parent root;
    //********************** Order Variables **********************\\
    OrderApi api;
    List<OrderRecord> orderRecords;
    private CheckoutPayload checkoutOrder;

    private ScheduledExecutorService executor;
    public void setCheckoutOrder(CheckoutPayload payload) {
        this.checkoutOrder = payload;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Platform.runLater(() -> {
            startUpdater();

        });
    }

    private void stopUpdater() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    private void startUpdater() {
        executor = Executors.newScheduledThreadPool(1);
        Runnable loop = new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        try {
                            api = new OrderApiImpl();
                            String check = OrderRecord.orderStatusToString(api.getOrderStatus(checkoutOrder.orderId));
                            System.out.println(check);
                            if (check.equals(OrderRecord.orderStatusToString(OrderStatus.ACCEPTED))) {
                                acceptedLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.READYTOCOOK))) {
                                acceptedLabel.setStyle("-fx-background-color: #7CFC00");
                                readyToCookLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.COOKING))) {
                                cookingLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.READY))) {
                                readyLabel.setStyle("-fx-background-color: #7CFC00");
                                //todo: someLabel.setText("Your order: " + checkoutOrderId.orderId + " is ready for pickup!");
                                api.removeOrder(checkoutOrder.orderId);
                                stopUpdater();
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        };
        executor.scheduleAtFixedRate(loop, 0, 1000, TimeUnit.MILLISECONDS);
    }
}
