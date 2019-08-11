package com.company;

public class ConverterApplication {

    public static void main(String[] args) {

        //instantiate
        ConverterIf monthConverter = new ConverterIf();
        ConverterIf dayConverter = new ConverterIf();

        ConverterSwitch monthCvrtSwitch = new ConverterSwitch();
        ConverterSwitch dayCvrtSwitch = new ConverterSwitch();

        //assigning the returned values from the methods
        String monthConvertIf = monthConverter.convertMonth(3);
        String dayConvertIf = dayConverter.convertDay(5);

        String monthConverterSwitch = monthCvrtSwitch.convertMonth(3);
        String dayConverterSwitch = dayCvrtSwitch.convertDay(5);

        //display the result of converterIf
        System.out.println("Your int value of a month has converted to --> " + monthConvertIf);
        System.out.println("Your int value of a day has converted to --> " + dayConvertIf);
        System.out.println("\n\n");

        //display the result of convertSwitch
        System.out.println("Your int value of a month has converted to --> " + monthConverterSwitch);
        System.out.println("Your int value of a day has converted to --> " + dayConverterSwitch);
    }
}
