package com.company;

public class Commons {

    //common properties
    private String name;
    private int strength;
    private int health;
    private int stamina;
    private int speed;
    private int attackPower;

    public Commons(String name, int strength, int health, int stamina, int speed, int attackPower) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.stamina = stamina;
        this.speed = speed;
        this.attackPower = attackPower;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    //IMPLEMENTING COMMON SKILLS OF FARMER / CONSTABLE / WARRIOR
    public void run (){
        System.out.println("I'm running!");
        //stamina will go down by 10.
        decreaseStamina();
        System.out.println("Stamina Decreased...");
    }
    public void attack(){
        System.out.println("I'm attacking! Roar!!!!!");
        //attack will decrease health by 15
        for (int i = 0; i < 3; i++){
            decreaseHealth();
        }
        System.out.println("Health Decreased...");
    }
    public void decreaseHealth(){
        //calling it once will decrease the stamina by 5
        int health = getHealth();
        setHealth(health - 5);
    }
    public void increaseStamina(){
        //calling it once will increase the stamina by 10
        int stamina = getStamina();
        setStamina(stamina + 10);
    }
    public void decreaseStamina(){
        //calling it once will decrease the stamina by 10
        int stamina = getStamina();
        setStamina(stamina - 10);
    }

}
