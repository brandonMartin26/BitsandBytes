package com.example.myjavafx.core.models;

import java.util.List;

public class PizzaUtils {

    public static List<String> pizzaRecordsToOrderList(List<PizzaRecord> pizzaRecords) {
        return pizzaRecords.stream().map(pizzaRecord -> {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(PizzaRecord.pizzaTypeToString(pizzaRecord.getType())+" Pizza\n");
            pizzaRecord.getToppings().stream().forEach(topping -> {
                stringBuilder.append("\t+ "+PizzaRecord.toppingToString(topping));
                stringBuilder.append("\n");
            });
            return stringBuilder.toString();
        }).toList();
    }
}
