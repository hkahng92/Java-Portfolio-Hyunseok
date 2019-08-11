package com.company;

import java.util.ArrayList;
import java.util.List;

public class Commons {

    private String name;
    private double price; //this will be the base price for the icecream.
    private List<String> flavors;
    private int quantity; //by oz


    public Commons(String name, double price,int quantity , List<String> flavors) {
        this.name = name;
        this.price = price;
        this.flavors = flavors;
        this.quantity = quantity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
