//Exercise 4: Array Fun (Arrays) -- checked

package com.company;

public class ArrayFunReverseIt {

    public static void main(String[] args) {

        //declaring an original array based on the instructions. this will be reversed
        int[] toBeReversed = {1,2,3,4,5};

        //based on the length of the original array, create a new array
        int[] reversed = new int[toBeReversed.length];
        int i = 0;

        //looping through the original array from the end to the beginning and assigning to
        //the new array
        for(int j = toBeReversed.length-1; j >= 0; j--){
            reversed[i] = toBeReversed[j];
            i++;
        }

        //printing both original and reversed array
        System.out.print("Original => [ ");
        for(int element: toBeReversed){
            System.out.print(element + " ");
        }
        System.out.print("]");

        System.out.print("\nReversed => [ ");
        for(int element: reversed){
            System.out.print(element + " " );
        }
        System.out.print("]");
        System.out.println("\n");
    }
}
