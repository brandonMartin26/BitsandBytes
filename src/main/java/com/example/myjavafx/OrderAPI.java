package com.example.myjavafx;

import java.io.*;
import java.util.ArrayList;

public final class OrderAPI {
    static String pathName = "Database/orderDB.txt";

    public static ArrayList<Order> getOrderInfo() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(pathName));

            String line;
            ArrayList<Order> orderList = new ArrayList<Order>();
            while ((line = reader.readLine()) != null) {
                String[] cartList = line.split("/");
                Order newOrder = new Order();
                for(String cartItemRaw : cartList) {
                    String[] cartItemStr = cartItemRaw.split(",");
                    String cartItem = cartItemStr[0];
                    int count = Integer.parseInt(cartItemStr[1]);
                    String[] itemStr = cartItem.split(";");
                    Item newItem = new Item(itemStr[0], itemStr[1], Double.parseDouble(itemStr[2]));
                    CartItem newCartItem = new CartItem(newItem, count);
                    newOrder.addToOrder(newCartItem);
                }
                orderList.add(newOrder);
            }
            return orderList;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<Order>();
        }
    }
    public static void saveData(ArrayList<Order> orderList){
        try{
            FileWriter writer = new FileWriter(pathName);
            for(Order order : orderList){
                System.out.println(order);
                writer.append(order + "\n");
            }
            writer.flush();
            writer.close();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
