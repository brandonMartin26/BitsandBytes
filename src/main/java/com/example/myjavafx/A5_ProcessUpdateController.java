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
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;

public class A5_ProcessUpdateController implements Initializable {

    @FXML private Label yourOrderIsLabel, acceptedLabel, readyToCookLabel, cookingLabel, readyLabel, timerLabel;
    @FXML private Button refreshOrder, chefViewTempBtn, processAgentOrderUpdateBtn;
    int counter, seconds, minutes;
    String lastChecked = "";

    private Stage stage;
    private Scene scene;
    private Parent root;
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
        startTimer();
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
                            if (check.equals(OrderRecord.orderStatusToString(OrderStatus.ACCEPTED))) {
                                acceptedLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.READYTOCOOK))) {
                                acceptedLabel.setStyle("-fx-background-color: #7CFC00");
                                readyToCookLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.COOKING))) {
                                cookingLabel.setStyle("-fx-background-color: #7CFC00");
                            } else if (check.equals(OrderRecord.orderStatusToString(OrderStatus.READY))) {
                                readyLabel.setStyle("-fx-background-color: #7CFC00");
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

    public void startTimer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                try {
                    api = new OrderApiImpl();
                    String check = OrderRecord.orderStatusToString(api.getOrderStatus(checkoutOrder.orderId));

                    Platform.runLater(() -> {
                        seconds=counter%60;
                        minutes=counter/60;
                        if (counter == 0 && check.equals(OrderRecord.orderStatusToString(OrderStatus.READYTOCOOK))) {
                            counter = 30 * 60;
                        } else if (counter == 0 && check.equals(OrderRecord.orderStatusToString(OrderStatus.ACCEPTED))) {
                            counter = 15 * 60;
                        } else if(check.equals(OrderRecord.orderStatusToString(OrderStatus.COOKING)) && !check.equals(lastChecked)){
                            counter = 15 * 60;
                        } else if(check.equals(OrderRecord.orderStatusToString(OrderStatus.READY))){
                            counter = 0;
                        }
                        lastChecked = check;

                        if(check.equals(OrderRecord.orderStatusToString(OrderStatus.READY))){
                            timerLabel.setText("Your order is ready!");
                        }
                        else if (seconds < 10 && minutes < 10) {
                            timerLabel.setText("0" + minutes + ":0" + seconds);
                        } else if (minutes < 10) {
                            timerLabel.setText("0" + minutes + ":" + seconds);
                        } else if (seconds < 10) {
                            timerLabel.setText(minutes + ":" + "0" + seconds);
                        } else {
                            timerLabel.setText(minutes + ":" + seconds);
                        }

                        counter--;
                        if (counter < 0){
                            counter = 0;
                        }
                    });
                } catch(Exception e){
                    throw new RuntimeException(e);
                }
            };
        }, 1000, 1000);
    }
}
