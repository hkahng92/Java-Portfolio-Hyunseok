package com.company;

import java.util.List;

public class Factory extends Commons {

    private double totalPrice;
    private int quantityPerFlavor;

    //constructor
    public Factory(String name, List<String> flavors, double price, int quantity) {
        super(name, price, quantity, flavors);
    }

    public void cartonPerFlavor(){
        //factory will make all flavors in even quantity
        //any leftovers won't be distributed so only the int is necessary
        int perFlavor = getFlavors().size();
        int quantityPerFlavor = getQuantity()/perFlavor;
        setQuantityPerFlavor(quantityPerFlavor);
    }

    public void calcFactoryPrice(){
        //quantity passed here will be number of cartons
        //price passed here will be a price per carton
        double price = getPrice() * getQuantity();
        setTotalPrice(price);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantityPerFlavor() {
        return quantityPerFlavor;
    }

    public void setQuantityPerFlavor(int quantityPerFlavor) {
        this.quantityPerFlavor = quantityPerFlavor;
    }
}




