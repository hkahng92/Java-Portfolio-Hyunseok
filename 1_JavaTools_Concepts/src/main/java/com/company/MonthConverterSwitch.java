//Exercise 1: Month Converter (Conditionals) -- checked

package com.company;
import java.util.Scanner;

public class MonthConverterSwitch {
    public static void main(String[] args) {
        //set up scanner
        Scanner scan = new Scanner(System.in);

        //do I need to account for users to input other than Ints? Do I only take Int?

        //ask the user to enter a number between 1 - 12
        System.out.print("Please enter a number between 1 and 12: ");
        int userIn = Integer.parseInt(scan.nextLine());
        System.out.print("\n");

        switch(userIn){
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("September");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("You have entered an invalid number. You must enter a number between 1 and 12. Goodbye.");
                break;
        }


    }
}
