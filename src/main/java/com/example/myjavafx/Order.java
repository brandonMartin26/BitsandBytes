package com.example.myjavafx;

import java.util.ArrayList;

public class Order {
    ArrayList<CartItem> cartList;
    public Order(){
        cartList = new ArrayList<>();

    }
    public void addToOrder(CartItem cartItem){
        cartList.add(cartItem);
    }
    public void removeFromOrder(CartItem cartItem){
        cartList.remove(cartList.indexOf(cartItem));
    }
    public ArrayList<CartItem> getCart(){
        return cartList;
    }
    @Override
    public String toString(){
        String orderString = "";
        for (int i = 0; i < cartList.size(); i++){
            orderString += cartList.get(i).toString();
            if(i != cartList.size()-1){
                orderString += "/";
            }
        }

        return orderString;
    }
}

