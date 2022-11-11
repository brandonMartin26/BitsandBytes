package com.example.myjavafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class OrderAPI {
    String pathName = "Database/orderDB.csv";

    public ArrayList getOrderInfo() {
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
    }
    public void saveData(ArrayList list){
        FileWriter writer = new FileWriter(pathName);
    }
}
