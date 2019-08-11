//checked

package com.company;

import java.util.Scanner;

public class ArrayFun5Words {

//    //array fun 5 words method
//    public static String[] arrayFunUserArray(String a,String b, String c,String d, String e){
//        String[] newArr = {a,b,c,d,e};
//        return newArr;
//    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userIn;
        String[] userArray = new String[5];

        for (int i = 0; i < userArray.length; i++) {
            System.out.print("Please enter a word: ");
            userIn = scan.nextLine();
            userArray[i] = userIn;
            //System.out.println(userArray[i]);
        }

        //String[] result = arrayFunUserArray(userArray[0], userArray[1], userArray[2], userArray[3], userArray[4]);
        System.out.print("Here is your array with components => [ ");
        for (String element : userArray) {
            System.out.print("\""+element + "\" ");
        }
        System.out.println("]");
    }
}
