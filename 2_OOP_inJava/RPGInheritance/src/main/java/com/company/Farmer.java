package com.company;

public class Farmer extends Commons{

    //Farmer's original properties. Has none

    //Farmer has a constructor.
    public Farmer(String name){
        super(name, 75, 100, 75, 10, 1);

        //if there are additional properties do below:
        //this is for constable

    }

    //Farmer's unique abitilies
    //harvest (decrease stamina by 8), plow (decrease stamina by 5)
    public void harvest(){
        System.out.println("I'm harvesting my field right now. Gotta feed my family.");
        //this decreses by 10
        decreaseStamina();
        //this will make stamina go down by 8 in total from this methed
        int farmerStamina = getStamina();
        setStamina(farmerStamina+2);
        System.out.println("Stamina Decreased...");
    }
    public void plow(){
        System.out.println("Now I'm plowing the field again to be able to harvest. ");
        //int farmerStamina = getStamina();
        //setStamina(farmerStamina-loweringStamina);
    }

    public void healFarmer(){
        System.out.println("I'm healing. Meditate meditate meditate");
        //int health = getHealth();
        //setHealth(health + 10);
        //super(name, 75, 100, 75, 10, 1);
        //heal resets all the stats
        setStrength(75);
        setHealth(100);
        setStamina(75);
        setSpeed(10);
        setAttackPower(1);

    }

}
