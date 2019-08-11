package com.company;

public class Warrior extends Commons{
    //ask about the shield strength. hardcoding it
    private int shieldStrength;

    public Warrior (String name){
        super(name, 75,100,100,50,10);
        this.shieldStrength = 100;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    //unique abilities
    //heal and decreaseShieldStrength
    public void healWarrior (){
        System.out.println("I'm healing. Meditate meditate meditate");
        setShieldStrength(75);
        setHealth(100);
        setStamina(100);
        setSpeed(50);
        setShieldStrength(100);
        setAttackPower(10);
    }
    public void decreaseShieldStrength(){
        System.out.println("Shield strength decreasd. Many effects from this skill");
        //decreases health by 20, decreases shieldstrength by 15 and increases stamina by 10
        decreaseHealth();
        decreaseHealth();
        decreaseHealth();
        decreaseHealth();
        int shieldStr = getShieldStrength();
        setShieldStrength(shieldStr - 30);
        increaseStamina();

    }
}
