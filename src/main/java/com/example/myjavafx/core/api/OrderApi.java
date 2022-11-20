package com.example.myjavafx.core.api;

import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;
import com.example.myjavafx.core.models.PizzaRecord;

import java.io.IOException;
import java.util.List;

public interface OrderApi {
    void addOrder(String orderId, List<PizzaRecord> pizzaRecords) throws IOException;
    void removeOrder(String orderId) throws IOException;
    OrderStatus getOrderStatus(String orderId) throws Exception;
    void setOrderStatus(String orderId, OrderStatus status) throws IOException;
    List<OrderRecord> getOrderRecords() throws IOException;
    List<PizzaRecord> getPizzaRecordsByOrderId(String orderId) throws IOException;
}
