package com.company;

public class Constable extends Commons{
        //Constable's original property
    private String jurisdiction;
    // this is a constructor for constable
    public Constable(String name, String jurisdiction) {
        super(name, 60,100,60,20,5);
        this.jurisdiction = jurisdiction;
    }

    //constable's own abilities
    //heal and arrest
    public void healConstable(){
        System.out.println("I'm healing. Meditate meditate meditate");
        //int health = getHealth();
        //setHealth(health + 10);
        //super(name, 75, 100, 75, 10, 1);
        //heal resets all the stats
        setStrength(60);
        setHealth(100);
        setStamina(60);
        setSpeed(20);
        setAttackPower(5);
    }
    public void arrest(){
        System.out.println("Arrested a criminal");
        decreaseHealth();
        decreaseStamina();
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
