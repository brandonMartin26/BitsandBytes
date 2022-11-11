package com.example.myjavafx;

import java.io.*;
import java.util.ArrayList;

public class OrderAPI {
    String pathName = "Database/orderDB.csv";

    public ArrayList<Item> getOrderInfo() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(pathName));

            String line = reader.readLine();
            ArrayList<Item> orderList = new ArrayList<Item>();
            while ((line = reader.readLine()) != null) {
                String[] detail = line.split(";");
                Item newItem = new Item(detail[0].trim(), detail[1].trim(),
                        Double.parseDouble(detail[3].trim()));
                orderList.add(newItem);
            }
            return orderList;
        } catch (Exception e){
            System.out.print("error1");
            return new ArrayList<Item>();
        }
    }
    public void saveData(ArrayList<Item> list){
        try{
            FileWriter writer = new FileWriter(pathName);
            for(Item object : list){
                Item item = (Item) object;
                writer.append((item.toStringOrderDB()) + "\n");
            }
        } catch(Exception e){
            System.out.print("error2");
        }
    }
}
