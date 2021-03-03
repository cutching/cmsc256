/**********************************************************************************
 * CustomDate.java
 * *********************************************************************************
 * Project 3: Date manipulation with the CustomDate class
 * CMSC 256-901
 * 3/1/2021
 * Gavin Cutchin
 **********************************************************************************/
package Project3.cmsc256;

import java.util.Objects;

public class CustomDate implements Comparable<CustomDate> {
    private int day;
    private int month;
    private int year;

    //default constructor sets date to 01/01/1900
    public CustomDate(){
        this.month = 1;
        this.day = 1;
        this.year = 1900;
    }

    //parameterized constructor take month,day, and year as an int
    public CustomDate(int month, int day, int year) throws IllegalArgumentException{
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    //parameterized constructor take month as a string and day and year as an int
    public CustomDate(String month, int day, int year) throws IllegalArgumentException{
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    //setMonth sets the string input to a number
    public void setMonth(String month) throws IllegalArgumentException {
        //sets the input to lowercase and finds the appropriate case
        switch(month.toLowerCase()){
            case "january":
                setMonth(1);
                break;
            case "february":
                setMonth(2);
                break;
            case "march":
                setMonth(3);
                break;
            case "april":
                setMonth(4);
                break;
            case "may":
                setMonth(5);
                break;
            case "june":
                setMonth(6);
                break;
            case "july":
                setMonth(7);
                break;
            case "august":
                setMonth(8);
                break;
            case "september":
                setMonth(9);
                break;
            case "october":
                setMonth(10);
                break;
            case "november":
                setMonth(11);
                break;
            case "december":
                setMonth(12);
                break;
            //if no match is found exception is thrown
            default: throw new IllegalArgumentException("Invalid month string.");
        }
    }

    //sets the month to the int month input if its between 1 and 12 inclusively, throws an exception otherwise
    public void setMonth(int month) throws IllegalArgumentException{
        if(month > 12 || month < 1){
            throw new IllegalArgumentException("Month must be between 1 and 12 inclusively.");
        }
        else{
            this.month = month;
        }
    }

    //sets the day to the input if the day is between 1 and the end of the month inclusively
    public void setDay(int day) throws IllegalArgumentException {
        if(day > daysInTheMonth() || day < 1){
            throw new IllegalArgumentException("Day must be between 1 and the end of the month inclusively");
        }
        else{
            this.day = day;
        }
    }

    //sets the year to the input if the input is between 0 and 10000, throws an exception otherwise
    public void setYear(int year) throws IllegalArgumentException{
        if(year <= 0 || year > 9999){
            throw new IllegalArgumentException("Year must be between 0 and 9999");
        }
        else{
            this.year = year;
        }
    }

    //returns the CustomDate month value
    public int getMonth() {
        return month;
    }

    //returns the CustomDate day value
    public int getDay() {
        return day;
    }

    //returns the CustomDate year value
    public int getYear() {
        return year;
    }

    //extra method to define the number of days in each month that also accounts for leap years
    public int daysInTheMonth(){
        if(this.month == 2 && isLeapYear()){
            return 29;
        }
        else if(this.month == 2){
            return 28;
        }
        else if(this.month == 1||this.month == 3||this.month == 5||this.month == 7
                ||this.month == 8||this.month == 10||this.month == 12){
            return 31;
        }
        else{
            return 30;
        }
    }
    /**
    *sets the date 7 days ahead and resets year to 0 if year is 9999
    *increments the year and month appropriately otherwise
     */
    public void advanceOneWeek(){
        if(day + 7 > daysInTheMonth()){
            if(month == 12){
                if(year == 9999){
                    day = 7 - (daysInTheMonth() - day);
                    month = 1;
                    year = 0;
                }
                else{
                    day = 7 - (daysInTheMonth() - day);
                    month = 1;
                    year++;
                }
            }
            else{
                day = 7 - (daysInTheMonth() - day);
                month++;
            }
        }
        else{
            day += 7;
        }
    }
    /**
    *sets the date 1 day and resets year to 0 if year is 9999
    *increments the year and month appropriately otherwise
     */
    public void advanceOneDay(){
        if(day == daysInTheMonth()){
            if(month == 12){
                if(year == 9999){
                    month = 1;
                    day = 1;
                    year = 0;
                }
                else{
                    month = 1;
                    day = 1;
                    year++;
                }
            }
            else{
                month++;
                day = 1;
            }
        }
        else{
            day++;
        }
    }

    /**
    *tests if the year is a leap year, if its divisible by 4 and not by 100 OR
    *divisible by 100 and divisible by 400
     */
    public boolean isLeapYear() {
        if( (this.year % 4 == 0 && this.year % 100 != 0)
                || (this.year % 100 == 0 && this.year % 400 == 0)){
            return true;
        }
        else{
            return false;
        }
    }

    //checks if the object in reference is the same as the current object
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof CustomDate)) {
            return false;
        }

        CustomDate that = (CustomDate) o;
        return day == that.day &&
                month == that.month &&
                year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    /**
     * compares two CustomDate objects
     * return 0 if they are the same date
     * return 1 if the first date is after the second date
     * return -1 if the first date is before the second date
     */
    @Override
    public int compareTo(CustomDate o) {
        if(this.year < o.getYear()){
            return -1;
        }
        else if(this.year == o.getYear()){
            if(this.getMonth() < o.getMonth()){
                return -1;
            }
            else if(this.getMonth() == o.getMonth()){
                if(this.getDay() < o.getDay()){
                    return -1;
                }
                else if(this.getDay() == o.getDay()){
                    return 0;
                }

            }
        }
        return 1;
    }

    //prints the date in the format "MM/DD/YYYY"
    @Override
    public String toString() {
        return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + String.format("%04d", year);
    }
}
