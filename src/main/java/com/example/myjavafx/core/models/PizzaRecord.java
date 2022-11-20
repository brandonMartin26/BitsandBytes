package com.example.myjavafx.core.models;

import com.example.myjavafx.core.models.enums.PizzaType;
import com.example.myjavafx.core.models.enums.Topping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaRecord {

    private String id;

    private PizzaType type;
    private List<Topping> toppings;

    public PizzaRecord(String id, PizzaType type, List<Topping> toppings) {
        this.id = id;
        this.type = type;
        this.toppings = toppings;
    }

    public String getId() {
        return id;
    }

    public PizzaType getType() {
        return type;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public String toRecord() {
        return this.id+";"+pizzaTypeToString(this.type)+";"+this.toppings.stream().map(PizzaRecord::toppingToString).collect(Collectors.joining(","));
    }

    public static PizzaRecord fromData(String record) {
        // Data Shape: orderId; type; topping-0, topping-n
        final String[] data = record.trim().split(";");
        final String id = data[0];
        final PizzaType type = pizzaTypeFromString(data[1]);
        List<Topping> toppings = Arrays.stream(data[2].split(",")).map(PizzaRecord::toppingFromString).toList();
        return new PizzaRecord(id, type, toppings);
    }

    public static Topping toppingFromString(String topping) {
        return switch (topping) {
            case "Mushrooms" -> Topping.MUSHROOMS;
            case "Onions" -> Topping.ONIONS;
            case "Olives" -> Topping.OLIVES;
            case "Extra-Cheese" -> Topping.EXTRA_CHEESE;
            default -> Topping.NONE;
        };
    }

    public static PizzaType pizzaTypeFromString(String type) {
        return switch (type) {
            case "Pepperoni" -> PizzaType.PEPPERONI;
            case "Vegetable" -> PizzaType.VEGETABLE;
            case "Cheese" -> PizzaType.CHEESE;
            default -> PizzaType.PLAIN;
        };
    }



    public static String toppingToString(Topping topping) {
        return switch (topping) {
            case MUSHROOMS -> "Mushrooms";
            case ONIONS -> "Onions";
            case OLIVES -> "Olives";
            case EXTRA_CHEESE -> "Extra-Cheese";
            default -> "None";
        };
    }

    public static String pizzaTypeToString(PizzaType type) {
        return switch (type) {
            case PEPPERONI -> "Pepperoni";
            case VEGETABLE -> "Vegetable";
            case CHEESE -> "Cheese";
            default -> "Plain";
        };
    }
}
