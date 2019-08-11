package com.company;

import com.company.interfaces.Converter;

public class ConverterSwitch implements Converter {

    //Same converter using switch
//    String convertMonth(int monthNumber);
//    String convertDay(int dayNumber);

    public String convertMonth(int monthNumber){
        String answer = "";

        switch(monthNumber){
            case 1:
                answer = "January";
                break;
            case 2:
                answer = "February";
                break;
            case 3:
                answer = "March";
                break;
            case 4:
                answer = "April";
                break;
            case 5:
                answer = "May";
                break;
            case 6:
                answer = "June";
                break;
            case 7:
                answer = "July";
                break;
            case 8:
                answer = "August";
                break;
            case 9:
                answer = "September";
                break;
            case 10:
                answer = "October";
                break;
            case 11:
                answer = "November";
                break;
            case 12:
                answer = "December";
                break;
            default:
                System.out.println("Invalid input. Good Bye.");
                break;
        }

        return answer;
    }

    public String convertDay(int dayNumber){
        String answer = "";

        switch(dayNumber){
            case 1:
                answer = "Sunday";
                break;
            case 2:
                answer = "Monday";
                break;
            case 3:
                answer = "Tuesday";
                break;
            case 4:
                answer = "Wednesday";
                break;
            case 5:
                answer = "Thursday";
                break;
            case 6:
                answer = "Friday";
                break;
            case 7:
                answer = "Saturday";
                break;
            default:
                answer = "Invalid input. Good Bye.";
                break;
        }

        return answer;
    }

}
