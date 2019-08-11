package com.company;

import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) {

        //creating list of flavors for store
        List<String> storeFlavors = new ArrayList<>();
        storeFlavors.add("Vanilla");
        storeFlavors.add("Strawberry");
        storeFlavors.add("Chocolate");
        storeFlavors.add("Mint");
 /*       for(String flavor: storeFlavors){
            System.out.println(flavor);
        }*/

        //Creating a ice cream store
        Store iceCreamStore = new Store("Tyler's Ice Cream", storeFlavors, 2.15,30);
        System.out.println("Welcome to " + iceCreamStore.getName() + "!");
        System.out.println("Our shop has following flavors available:");
        System.out.println(iceCreamStore.getFlavors());
        System.out.println("\nHere is our menu: (*price per scoop)");
        for(String flavor: storeFlavors) {
            System.out.printf( "%s: $%.2f\n", flavor,iceCreamStore.getPrice());
        }
        //setting the prices
        iceCreamStore.milkShakePrice();
        iceCreamStore.iceCreamCakePrice();
        iceCreamStore.sundaePrice();

        System.out.println("Special Menu");
        System.out.printf("Ice Cream Sundae: $%.2f\n",iceCreamStore.getSundaePrice());
        System.out.printf("Milk Shake: $%.2f\n",iceCreamStore.getMilkShakePrice());
        System.out.printf("Ice Cream Cake: $%.2f\n",iceCreamStore.getIceCreamCakePrice());

        System.out.println("Making Ice Cream Cake...");
        iceCreamStore.makeIceCreamCake();
        System.out.println("Making Ice Cream Sundae...");
        iceCreamStore.makeSundae();
        System.out.println("Making Milk Shake...");
        iceCreamStore.makeMilkShake();
        System.out.println("Scoops left: " + iceCreamStore.getQuantity());
        System.out.println("\n");

        //creating list of flavors for store
        List<String> factoryFlavors = new ArrayList<>();
        factoryFlavors.add("Vanilla");
        factoryFlavors.add("Strawberry");
        factoryFlavors.add("Chocolate");
        factoryFlavors.add("Cheese Cake");
        factoryFlavors.add("Coffee");
        factoryFlavors.add("Mint");
        //Creating a ice cream store
        Factory iceFactory = new Factory("Tyler's Ice Cream Factory", factoryFlavors, 215.34,80);
        System.out.println("This is the Ice Cream Factory:");
        iceFactory.calcFactoryPrice();
        iceFactory.cartonPerFlavor();
        System.out.println("Total price of the ice cream made in the factory is: $" + iceFactory.getTotalPrice());
        System.out.println("Number of cartons being made per flavor: " + iceFactory.getQuantityPerFlavor());

    }
}
