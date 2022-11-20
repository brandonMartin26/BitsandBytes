package com.example.myjavafx;

import com.example.myjavafx.core.models.PizzaRecord;

import java.util.List;

public class CheckoutPayload {
    final String orderId;
    final List<PizzaRecord> pizzas;

    CheckoutPayload(String orderId, List<PizzaRecord> pizzas) {
        this.orderId = orderId;
        this.pizzas = pizzas;
    }
}
