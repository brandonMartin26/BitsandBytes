package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class A6_ProcessAgentController implements Initializable {
    @FXML
    private ListView processAgentListView;

    @FXML
    private Button acceptOrderButton;

    private Stage stage;
    private Scene scene;
    private Parent root;
    // Left Side Panel
    @FXML
    private ListView<String> orderListView;

    // Middle Panel
    @FXML
    private ListView<String> orderPizzaDetailsListView;

    // ChoiceBox in Right Side Panel
    @FXML
    private ChoiceBox<String> orderStatusChoiceBox;

    private OrderApi api;

    List<OrderRecord> orderRecords;
    List<OrderStatus> processingAgentOrderStatuses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api = new OrderApiImpl();
        processingAgentOrderStatuses = Arrays.asList(OrderStatus.CREATED);
        try {
            // 1st pull records into "orderRecords"
            orderRecords = getOrderRecords();
            // Updates the left hand panel that displays the order Records
            updateOrderList(orderRecords);
            // Take OrderStatus values and map to strings that are put into ChoiceBox
            orderStatusChoiceBox.getItems().addAll(Arrays.asList(OrderStatus.CREATED, OrderStatus.ACCEPTED).stream().map(OrderRecord::orderStatusToString).toList());
            // Listener for ChoiceBox
            orderStatusChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.isEmpty()) return;
                    // Update the order record in the database with the new OrderStatus for the associated orderId.
                    final OrderStatus newStatus = OrderRecord.orderStatusFromString(newValue);
                    try {
                        final String orderId = orderListView.getSelectionModel().getSelectedItem();
                        if (newStatus == OrderStatus.ACCEPTED) {
                            api.setOrderStatus(orderId, OrderStatus.READYTOCOOK);
                            orderListView.getSelectionModel().clearSelection();
                            orderPizzaDetailsListView.getItems().clear();
                            orderStatusChoiceBox.getSelectionModel().clearSelection();
                            orderStatusChoiceBox.hide();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            //Left side Panel Listener -> will update and (display) on Middle Panel
            orderListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null && !newValue.isEmpty()) {
                        // Update the Middle panel view to display all the pizzas for the selected order.
                        updateOrderDetailList();
                    }
                }
            });

            // Kickoff a periodic function which checks for changes to the database so we can update the view accordingly.
            setupUpdater();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderRecord> getOrderRecords() throws IOException {
        return api.getOrderRecords().stream().filter(orderRecord -> processingAgentOrderStatuses.contains(orderRecord.getStatus())).toList();
    }

    private void setupUpdater() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable loop = new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        try {
                            final List<OrderRecord> updatedOrderRecords = getOrderRecords();
                            // if known List of order Records != new List of order Records then update UI
                            if (orderRecords.size() != updatedOrderRecords.size()) {
                                orderRecords = updatedOrderRecords;
                                // isShowing = current choicebox state
                                final boolean isShowing = orderStatusChoiceBox.isShowing();
                                //selectedOrderId = OrderID currently selected
                                final String selectedOrderId = orderListView.getSelectionModel().getSelectedItem();
                                // Update Order List to updated List
                                updateOrderList(updatedOrderRecords);

                                final int indexOfSelectedOrder = orderListView.getItems().indexOf(selectedOrderId);
                                orderListView.scrollTo(indexOfSelectedOrder);
                                //(Magic)
                                orderListView.getSelectionModel().select(indexOfSelectedOrder);
                                if (isShowing) orderStatusChoiceBox.show();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        };
        executor.scheduleAtFixedRate(loop, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void updateOrderChoiceBox() throws Exception {
        final String selectedOrderId = orderListView.getSelectionModel().getSelectedItem();
        final String selectedOrderStatus = OrderRecord.orderStatusToString(api.getOrderStatus(selectedOrderId));
        final int selectedOrderStatusIndex = orderStatusChoiceBox.getItems().indexOf(selectedOrderStatus);
        orderStatusChoiceBox.getSelectionModel().select(selectedOrderStatusIndex);
    }

    private void updateOrderList(List<OrderRecord> orders) throws IOException {
        final List<String> orderIds = orders.stream().map(OrderRecord::getId).toList();
        orderListView.getItems().setAll(orderIds);
    }

    private void updateOrderDetailList(boolean... clear) {
        // Get id selected from "orderListView" to store in "selectedOrderId"
        final String selectedOrderId = orderListView.getSelectionModel().getSelectedItem();
        try {
            // Store Pizzas in List based in ID selected
            final List<PizzaRecord> pizzaRecords = api.getPizzaRecordsByOrderId(selectedOrderId);
            // Store formatted pizza strings in String List
            List<String> pizzaItems = pizzaRecords.stream().map(pizzaRecord -> {
                final StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(PizzaRecord.pizzaTypeToString(pizzaRecord.getType())+" Pizza\n");
                pizzaRecord.getToppings().stream().forEach(topping -> {
                    stringBuilder.append("\t+ "+PizzaRecord.toppingToString(topping));
                    stringBuilder.append("\n");
                });
                return stringBuilder.toString();
            }).toList();

            orderPizzaDetailsListView.getItems().setAll(pizzaItems);
            updateOrderChoiceBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}