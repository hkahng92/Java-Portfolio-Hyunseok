package com.company;

import java.util.List;

public class Store extends Commons{

    //store's own properties
    private double sundaePrice;
    private double milkShakePrice;
    private double iceCreamCakePrice;


    //constructor
    public Store (String name, List<String> flavors, double price, int quantity){
        super(name,price,quantity,flavors);
    }

    //store's unique methods
    public void makeSundae(){
        int iceCreamLeft = getQuantity();
        setQuantity(iceCreamLeft - 5);
    }
    public void sundaePrice(){
        double price = getPrice();
        setSundaePrice(price * 5);
    }
    public void makeMilkShake(){
        int iceCreamLeft = getQuantity();
        setQuantity(iceCreamLeft - 2);
    }
    public void milkShakePrice(){
        double price = getPrice();
        setMilkShakePrice(price * 2);
    }
    public void makeIceCreamCake(){
        int iceCreamLeft = getQuantity();
        setQuantity(iceCreamLeft - 10);
    }
    public void iceCreamCakePrice(){
        double price = getPrice();
        setIceCreamCakePrice(price * 10);
    }


    //getters and setters
    public double getSundaePrice() {
        return sundaePrice;
    }
    public void setSundaePrice(double sundaePrice) {
        this.sundaePrice = sundaePrice;
    }
    public double getMilkShakePrice() {
        return milkShakePrice;
    }
    public void setMilkShakePrice(double milkShakePrice) {
        this.milkShakePrice = milkShakePrice;
    }
    public double getIceCreamCakePrice() {
        return iceCreamCakePrice;
    }
    public void setIceCreamCakePrice(double iceCreamCakePrice) {
        this.iceCreamCakePrice = iceCreamCakePrice;
    }
}
