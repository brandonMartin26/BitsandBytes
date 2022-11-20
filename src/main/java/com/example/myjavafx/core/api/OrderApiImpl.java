package com.example.myjavafx.core.api;

import com.example.myjavafx.core.database.TextDatabase;
import com.example.myjavafx.core.database.IDatabase;
import com.example.myjavafx.core.models.enums.OrderStatus;
import com.example.myjavafx.core.models.OrderRecord;
import com.example.myjavafx.core.models.PizzaRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// CRUD API: i.e. Create Read Update Delete API
public class OrderApiImpl implements OrderApi {
    /** Paths to our relational databases (text-based) files */
    private static final String orderPizzasDbPath = "Database/pizzas.txt";
    private static final String orderStatusDbPath = "Database/orders.txt";

    IDatabase pizzaRecordDB;
    IDatabase orderRecordDB;

    public OrderApiImpl() {
        this.pizzaRecordDB = new TextDatabase(orderPizzasDbPath);
        this.orderRecordDB = new TextDatabase(orderStatusDbPath);
    }

    @Override
    public void addOrder(String orderId, List<PizzaRecord> pizzaRecords) throws IOException {
        orderRecordDB.writeRecord(new OrderRecord(orderId, OrderStatus.CREATED).toRecord());
        pizzaRecordDB.writeRecords(pizzaRecords.stream().map(PizzaRecord::toRecord).toList());
    }


    @Override
    public void removeOrder(String orderId) throws IOException {
        orderRecordDB.deleteRecord(orderId);
        pizzaRecordDB.deleteRecord(orderId);
    }


    @Override
    public void setOrderStatus(String orderId, OrderStatus status) throws IOException {
        orderRecordDB.updateRecord(orderId, new OrderRecord(orderId, status).toRecord());
    }

    @Override
    public OrderStatus getOrderStatus(String orderId) throws Exception {
        Optional<OrderRecord> maybeOrder = getOrderRecords().stream().filter(record -> record.getId().equals(orderId)).findFirst();
        if (maybeOrder.isPresent()) {
            return maybeOrder.get().getStatus();
        }
        // todo: improve this lol
        throw new Exception("Uh-Oh Spaghettios! No order found");
    }

    @Override
    public List<PizzaRecord> getPizzaRecordsByOrderId(String orderId) throws IOException {
        return getOrderPizzaRecordsByOrderId(orderId).stream().map(PizzaRecord::fromData).toList();
    }

    public List<OrderRecord> getOrderRecords() throws IOException {
        List<OrderRecord> found = new ArrayList<>();
        return orderRecordDB.readRecords().stream().map(OrderRecord::fromRecord).filter(orderRecord -> {
            if (found.stream().anyMatch(uniqueRecord -> uniqueRecord.getId().equals(orderRecord.getId()))) return false;
            found.add(orderRecord);
            return true;
        }).toList();
    }

    // ------------- DB Data Transformers ------------- \\

    // Takes in an orderID
    // Returns the full list of strings for "OrderID; PizzaType; Toppings..." associated with that OrderID
    private List<String> getOrderPizzaRecordsByOrderId(String orderId) throws IOException {
        return pizzaRecordDB.readRecords().stream()
                .filter(Objects::nonNull)
                .filter(pizzaRecord -> pizzaRecord.trim().split(";")[0].equals(orderId)).toList();
    }
}
