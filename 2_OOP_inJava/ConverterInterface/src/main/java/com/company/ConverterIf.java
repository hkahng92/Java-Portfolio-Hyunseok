package com.company;

import com.company.interfaces.Converter;

public class ConverterIf implements Converter {

    public String convertMonth(int monthNumber){
        String answer = "";

        if(monthNumber == 1){
            answer = "January";
        } else if (monthNumber == 2){
            answer = "February";
        } else if (monthNumber == 3){
            answer = "March";
        } else if (monthNumber == 4){
            answer = "April";
        } else if (monthNumber == 5){
            answer = "May";
        } else if (monthNumber == 6){
            answer = "June";
        } else if (monthNumber == 7){
            answer = "July";
        } else if (monthNumber == 8){
            answer = "August";
        } else if (monthNumber == 9){
            answer = "September";
        } else if (monthNumber == 10){
            answer = "October";
        } else if (monthNumber == 11){
            answer = "November";
        } else if (monthNumber == 12){
            answer = "December";
        } else {
            answer = "invalid input. Good Bye. ";
        }

        return answer;
    }

    public String convertDay(int dayNumber){
        String answer = "";

        if(dayNumber == 1){
            answer = "Sunday";
        } else if (dayNumber == 2){
            answer = "Monday";
        } else if (dayNumber == 3){
            answer = "Tuesday";
        } else if (dayNumber == 4){
            answer = "Wednesday";
        } else if (dayNumber == 5){
            answer = "Thursday";
        } else if (dayNumber == 6){
            answer = "Friday";
        } else if (dayNumber == 7){
            answer = "Saturday";
        } else {
            answer = "invalid input. Good Bye. ";
        }


        return answer;
    }
}
