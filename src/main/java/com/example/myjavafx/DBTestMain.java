package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.PizzaRecord;
import com.example.myjavafx.core.models.enums.OrderStatus;
import com.example.myjavafx.core.models.enums.PizzaType;
import com.example.myjavafx.core.models.enums.Topping;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DBTestMain {
    public static void main(String[] args) throws Exception {
        OrderApiImpl orderApi = new OrderApiImpl();

        // Create 10 random new orders 0001-0010 \
        Random randomType = new Random();
        Random randomTopping = new Random();
        for (int i = 1; i <= 101; i++) {
            String orderId = String.format("%04d", i);
            PizzaType pizzaType = PizzaType.values()[randomType.nextInt(PizzaType.values().length)];
            List<Topping> pizzaToppings = List.of(Topping.values()[randomTopping.nextInt(Topping.values().length)]);
            PizzaRecord pizzaRecord = new PizzaRecord(orderId, pizzaType, pizzaToppings);
            orderApi.addOrder(orderId, List.of(pizzaRecord));
        }

        orderApi.getOrderRecords().forEach(orderRecord -> {
            try {
                orderApi.setOrderStatus(orderRecord.getId(), OrderStatus.READYTOCOOK);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        // remove order \
        String orderId = "0001";
        System.out.println("Removing order " + orderId);
        orderApi.removeOrder(orderId);

        // read order status \
        orderId = "0002";
        OrderStatus orderStatus = orderApi.getOrderStatus(orderId);
        System.out.println("Order Status: " + orderStatus);

        // update order status \
        orderId = "0002";
        orderApi.setOrderStatus(orderId, OrderStatus.COOKING);

        // re-read order status \
        orderId = "0002";
        orderStatus = orderApi.getOrderStatus(orderId);
        System.out.println("Order Status: " + orderStatus);

    }
}
