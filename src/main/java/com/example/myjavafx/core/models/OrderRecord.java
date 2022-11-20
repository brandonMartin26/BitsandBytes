package com.example.myjavafx.core.models;

import com.example.myjavafx.core.models.enums.OrderStatus;

public class OrderRecord {
    String id;
    OrderStatus status;

    public OrderRecord(String id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String toRecord() {
        return this.id+";"+ orderStatusToString(this.status);
    }

    public static OrderRecord fromRecord(String record) {
        // Data Shape: orderId; orderStatus
        String[] data = record.split(";");
        return new OrderRecord(data[0], orderStatusFromString(data[1]));
    }


    // todo add error handling if it doesn't match any
    public static OrderStatus orderStatusFromString(String orderStatus) {
        return switch(orderStatus) {
            case "Accepted" -> OrderStatus.ACCEPTED;
            case "ReadyToCook" -> OrderStatus.READYTOCOOK;
            case "Cooking" -> OrderStatus.COOKING;
            case "Ready" -> OrderStatus.READY;
            default -> OrderStatus.CREATED;
        };
    }

    public static String orderStatusToString(OrderStatus orderStatus) {
        return switch(orderStatus) {
            case ACCEPTED -> "Accepted";
            case READYTOCOOK -> "ReadyToCook";
            case COOKING -> "Cooking";
            case READY -> "Ready";
            default -> "Created";
        };
    }

}
