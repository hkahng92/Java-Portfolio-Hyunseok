package com.company;

public class Calculator {
    //will be using a concrete class implementation to create an object
    //this class will contain set of methods that does addition, subtraction, multiplication, division

    private int intOne;
    private int intTwo;
    private double doubleOne;
    private double doubleTwo;

//    public Calculator(int intOne, int intTwo, double doubleOne, double doubleTwo){
//        this.intOne = intOne;
//        this.intTwo = intTwo;
//        this.doubleOne = doubleOne;
//        this.doubleTwo = doubleTwo;
//    }
    public Calculator(){

    }

    //calculation methods ints and double
    //addition
    public int Addition (int a, int b){
        return a + b;
    }
    public double Addition(double a, double b){
        return a + b;
    }
    //subtraction
    public int Subtract (int a, int b){
        return a - b;
    }
    public double Subtract (double a, double b){
        return a - b;
    }
    //multiply
    public int Multiply(int a, int b){
        return a * b;
    }
    public double Multiply(double a, double b){
        return a * b;
    }
    //division
    public int Divide (int a , int b){
        return a / b;
    }
    public double Divide (double a, double b ){
        return a / b;
    }


    //getters and setters for the properties
    public int getIntOne() {
        return intOne;
    }

    public void setIntOne(int intOne) {
        this.intOne = intOne;
    }

    public int getIntTwo() {
        return intTwo;
    }

    public void setIntTwo(int intTwo) {
        this.intTwo = intTwo;
    }

    public double getDoubleOne() {
        return doubleOne;
    }

    public void setDoubleOne(double doubleOne) {
        this.doubleOne = doubleOne;
    }

    public double getDoubleTwo() {
        return doubleTwo;
    }

    public void setDoubleTwo(double doubleTwo) {
        this.doubleTwo = doubleTwo;
    }
}
