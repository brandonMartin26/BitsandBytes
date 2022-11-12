package com.example.myjavafx;

public class CartItem {
    private Item item;
    private int count;
    public CartItem(Item item) {
        this.item = item;
        this.count = 1;
    }
    public CartItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public void addOne() {
        this.count++;
    }

    public void removeOne() {
        this.count--;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return this.count;
    }

    public void setAmount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return item.getPrice() * this.count;
    }

    @Override
    public String toString() {
        return item + "," + count ;
    }
}
