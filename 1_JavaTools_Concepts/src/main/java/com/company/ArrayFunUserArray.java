//Exercise 4: Array Fun (Arrays) -- checked

package com.company;
import java.util.Scanner;

public class ArrayFunUserArray {

//    //array fun user array method. this method will take integers from the user and
//    //turn those into a single array
//    public static int[] arrayFunUserArray(int a, int b, int c, int d, int e) {
//        int[] newArr = {a, b, c, d, e};
//        return newArr;
//    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userIn;
        //already know that user is going to input 5 integers so set the new int array length at 5
        int[] userArray = new int[5];

        //based on the length of the new array, loop that many times and take in the user input and insert it in
        //the user's array
        for (int i = 0; i < userArray.length; i++) {
            System.out.print("Please enter a number: ");
            userIn = Integer.parseInt(scan.nextLine());
            userArray[i] = userIn;
            //System.out.println(userArray[i]);
        }

        //int[] result = arrayFunUserArray(userArray[0], userArray[1], userArray[2], userArray[3], userArray[4]);

        //print out the user's array
        System.out.print("Here are your array elements => [ ");
        for (int element : userArray) {

            System.out.print(element + " ");
        }
        System.out.print("]");


    }
}
