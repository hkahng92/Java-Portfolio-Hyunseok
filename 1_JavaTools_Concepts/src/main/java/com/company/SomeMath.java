//Exercise 3: Some Math (Methods) -- checked

package com.company;
public class SomeMath {

     //total method
    public static int total(int a, int b, int c, int d, int e){
        return a+b+c+d+e;
    }

    //average method
    public static double average5(int a,int b,int c, int d, int e){
        return (a+b+c+d+e)/5;
    }

    //largest5 method
    public static double largest5(double a, double b, double c, double d, double e){
        double[] findLargest = {a,b,c,d,e};
        double largestNum = Double.MIN_VALUE;
        for(int i = 0; i < findLargest.length; i++){
            if(findLargest[i] > largestNum){
                largestNum = findLargest[i];
            }
        }
        return largestNum;
    }

    public static void main(String[] args) {
        System.out.println("Total is: " + total(1,2,3,4,5));
        System.out.println("Average is: "+ average5(1,3,5,7,9));
        System.out.println("Largest number is: " + largest5(42.0,35.1,2.3,40.2,15.6));
    }
}
