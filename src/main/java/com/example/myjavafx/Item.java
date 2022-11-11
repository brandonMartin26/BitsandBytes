package com.example.myjavafx;

public class Item {
    private String pizzaType;
    private String pizzaTopping;
    private double price;

    public Item(){
        this.pizzaType = "No name";
        this.price = 0;
        this.pizzaTopping = "No topping";}

    public Item(String pizzaType, String pizzaToppping, double price) {
            this.pizzaType = pizzaType;
            this.pizzaTopping = pizzaToppping;
            this.price = price;
        }

        public String getPizzaType() {
            return this.pizzaType;
        }

        public void setPizzaType(String type) {
            this.pizzaType = type;
        }

        public String getPizzaTopping() {
        return this.pizzaTopping;
        }

        public void setPizzaTopping(String topping) {
        this.pizzaTopping = topping;
        }

        public double getPrice() {
            return this.price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
