package com.company;

public class CalculatorMain {

    public static void main(String[] args) {

        //practicing method overloading.
        //instantiate the calculator
        Calculator calc = new Calculator();
        System.out.println("INTEGER CALCULATIONS");
        System.out.println("This is the integer addition of 1 + 1 => " + calc.Addition(1,1));
        System.out.println("This is the integer subtraction of 23 - 52 => " + calc.Subtract(23,52));
        System.out.println("This is the integer multiplication of 34 * 2 => " + calc.Multiply(34,2));
        //ask if the return should be int or double for int divisions
        System.out.println("This is the integer division of 12 / 3 => " + calc.Divide(12,3));
        System.out.println("This is the integer division of 12 / 7 => " + calc.Divide(12,7));


        //double calculations
        System.out.println("\nDOUBLE CALCULATIONS");
        System.out.println("This is the integer addition of 3.4 + 2.3 => " + calc.Addition(3.4,2.3));
        System.out.println("This is the integer multiplication of 6.7 * 4.4 => " + calc.Multiply(6.7,4.4));
        System.out.println("This is the integer subtraction of 5.5 - 0.5 => " + calc.Subtract(5.5,0.5));
        System.out.println("This is the integer division of 10.8 / 2.2 => " + calc.Divide(10.8,2.2));

    }
}
