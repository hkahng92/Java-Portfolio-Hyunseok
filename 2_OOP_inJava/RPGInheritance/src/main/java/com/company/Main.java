package com.company;

public class Main {

    public static void main(String[] args) {

        Farmer farmer = new Farmer("Eric");
        Constable constable = new Constable("John","Bergen County");
        Warrior warrior = new Warrior("Tyler");

        //testing farmer
        System.out.println("####BEFORE ABILITIES#####");
        System.out.println("Current Farmer's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + farmer.getName());
        System.out.println("Strength: " + farmer.getStrength());
        System.out.println("Health: " + farmer.getHealth());
        System.out.println("Stamina: " + farmer.getStamina());
        System.out.println("Speed: " + farmer.getSpeed());
        System.out.println("Attack Power: " + farmer.getAttackPower());
        System.out.println("=========================");
        System.out.printf("\n");
        //methods commons
        System.out.println("##PERFORMING ABILITIES###");
        farmer.run();
        System.out.printf("\n");
        farmer.attack();
        System.out.printf("\n");
        farmer.harvest();
        System.out.printf("\n");
        farmer.healFarmer();
        System.out.printf("\n");
        farmer.harvest();

        System.out.printf("\n");
        System.out.println("#####AFTER ABILITIES#####");
        System.out.println("Current Farmer's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + farmer.getName());
        System.out.println("Strength: " + farmer.getStrength());
        System.out.println("Health: " + farmer.getHealth());
        System.out.println("Stamina: " + farmer.getStamina());
        System.out.println("Speed: " + farmer.getSpeed());
        System.out.println("Attack Power: " + farmer.getAttackPower());
        System.out.println("=========================");
        System.out.printf("\n");


        //testing constable
        System.out.println("####BEFORE ABILITIES#####");
        System.out.println("Current Constable's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + constable.getName());
        System.out.println("Strength: " + constable.getStrength());
        System.out.println("Health: " + constable.getHealth());
        System.out.println("Stamina: " + constable.getStamina());
        System.out.println("Speed: " + constable.getSpeed());
        System.out.println("Attack Power: " + constable.getAttackPower());
        System.out.println("=========================");
        System.out.printf("\n");

        System.out.println("##PERFORMING ABILITIES###");
        constable.run();
        System.out.printf("\n");
        constable.attack();
        System.out.printf("\n");
        constable.healConstable();
        System.out.printf("\n");
        constable.arrest();

        System.out.printf("\n");
        System.out.println("#####AFTER ABILITIES#####");
        System.out.println("Current Constable's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + constable.getName());
        System.out.println("Strength: " + constable.getStrength());
        System.out.println("Health: " + constable.getHealth());
        System.out.println("Stamina: " + constable.getStamina());
        System.out.println("Speed: " + constable.getSpeed());
        System.out.println("Attack Power: " + constable.getAttackPower());
        System.out.println("=========================");
        System.out.printf("\n");


        //testing warrior
        System.out.println("####BEFORE ABILITIES#####");
        System.out.println("Current warrior's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + warrior.getName());
        System.out.println("Strength: " + warrior.getStrength());
        System.out.println("Health: " + warrior.getHealth());
        System.out.println("Stamina: " + warrior.getStamina());
        System.out.println("Speed: " + warrior.getSpeed());
        System.out.println("Attack Power: " + warrior.getAttackPower());
        //ask about the shield strength. hardcoding it
        System.out.println("Shield Strength: " + warrior.getShieldStrength());
        System.out.println("=========================");
        System.out.printf("\n");

        System.out.println("##PERFORMING ABILITIES###");
        warrior.run();
        System.out.printf("\n");
        warrior.attack();
        System.out.printf("\n");
        warrior.healWarrior();
        System.out.printf("\n");
        warrior.decreaseShieldStrength();

        System.out.printf("\n");
        System.out.println("#####AFTER ABILITIES#####");
        System.out.println("Current warrior's state: ");
        System.out.println("=========================");
        System.out.println("Name: " + warrior.getName());
        System.out.println("Strength: " + warrior.getStrength());
        System.out.println("Health: " + warrior.getHealth());
        System.out.println("Stamina: " + warrior.getStamina());
        System.out.println("Speed: " + warrior.getSpeed());
        System.out.println("Attack Power: " + warrior.getAttackPower());
        //ask about the shield strength. hardcoding it
        System.out.println("Shield Strength: " + warrior.getShieldStrength());
        System.out.println("=========================");
        System.out.printf("\n");
    }
}
