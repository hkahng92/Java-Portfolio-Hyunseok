//Exercise 1: Month Converter (Conditionals) -- checked

package com.company;
import java.util.Scanner;

public class MonthConverterIf {
    public static void main(String[] args) {
        //set up scanner
        Scanner scan = new Scanner(System.in);

        //do I need to account for users to input other than Ints? Do I only take Int?

        //ask the user to enter a number between 1 - 12
        System.out.print("Please enter a number between 1 and 12: ");
        int userIn = Integer.parseInt(scan.nextLine());
        System.out.print("\n");

        if(userIn == 1){
            System.out.println("January");
        } else if(userIn == 2){
            System.out.println("February");
        } else if(userIn == 3){
            System.out.println("March");
        } else if(userIn == 4){
            System.out.println("April");
        } else if(userIn == 5){
            System.out.println("May");
        } else if(userIn == 6){
            System.out.println("June");
        } else if(userIn == 7){
            System.out.println("July");
        } else if(userIn == 8){
            System.out.println("August");
        } else if(userIn == 9){
            System.out.println("September");
        } else if(userIn == 10){
            System.out.println("October");
        } else if(userIn == 11){
            System.out.println("November");
        } else if(userIn == 12){
            System.out.println("December");
        } else {
            System.out.println("You have entered an invalid number. You must enter a number between 1 and 12. Goodbye.");
        }


    }
}
