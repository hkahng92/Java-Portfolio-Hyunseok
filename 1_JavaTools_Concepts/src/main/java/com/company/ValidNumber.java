//Exercise 2: Valid Number (Loops) -- checked BUT SHOULD I ALSO CHECK FOR OTHER CHARACTERS?

package com.company;
import java.util.Scanner;

public class ValidNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userIn = Integer.MIN_VALUE;

        while(userIn < 1 || userIn > 10){
            System.out.print("Please enter a number between 1 and 10: ");
            userIn = Integer.parseInt(scan.nextLine());
            if(userIn > 0 && userIn < 11){
                break;
            }
            System.out.println("You must enter a number between 1 and 10, please try again.\n");
        }

        System.out.println("Valid number! Here is what you have inserted: " + userIn);


    }
}
