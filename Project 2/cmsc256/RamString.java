/**********************************************************************************
 * RamString.java
 * *********************************************************************************
 * Project 2: Implementation of WackyStringInterface
 * CMSC 256-901
 * 2/17/2021
 * Gavin Cutchin
 **********************************************************************************/


package Project2.cmsc256;

import java.util.ArrayList;

public class RamString implements WackyStringInterface {

    private String input;
    /* default constructor */
    public RamString(){
        setWackyString("Let's Go Rams and CS@VCU!");
    }
    /* parameterized constructor, throws exception on null input */
    public RamString(String input) throws IllegalArgumentException{
            setWackyString(input);
    }

    @Override
    public void setWackyString(String string) throws IllegalArgumentException{
        if(string == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        else {
            input = string;
        }
    }
    /* returns the current RamString string */
    @Override
    public String getWackyString() {
        return input;
    }
    /*if length is less than three there is no third character
    * modulus 3 of position plus one to find intervals of 3
    * */
    @Override
    public String getEveryThirdCharacter() {

        if(input.length() < 3){
            return "";
        }
        else{
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < input.length(); i++){
                if((i + 1) % 3 == 0){
                    builder.append(input.charAt(i));
                }
            }
            return builder.toString();
        }
    }
    /*checks for even or odd input first and throws exception when it's not either*/
    @Override
    public String getEvenOrOddCharacters(String evenOrOdd) throws IllegalArgumentException {
        StringBuilder builder = new StringBuilder();
        if(evenOrOdd.equals("odd") && input.length() > 0){
            //every odd number appended to the string builder
            for(int i = 0; i < input.length(); i++){
                if(i % 2 == 0){
                    builder.append(input.charAt(i));
                }
            }
            return builder.toString();
        }
        else if(evenOrOdd.equals("even") && input.length() > 0){
            //every even number appended to the string builder
            for(int i = 0; i < input.length(); i++){
                if(i % 2 == 1){
                    builder.append(input.charAt(i));
                }
            }
            return builder.toString();
        }
        else if(!evenOrOdd.equals("even") && !evenOrOdd.equals("odd")){
            //invalid parameter
            throw new IllegalArgumentException("Invalid Input");
        }
        else{
            //when the length is 0 but the parameter was valid
            return "";
        }
    }

    @Override
    public int countDoubleDigits() {
         int count = 0;

         // String[] temp = input.split("");
         String[] temp = new String[input.length()];
         for(int i = 0; i < input.length(); i++){
             temp[i] = input.substring(i,i+1);
         }

            for(int i = 0; i < temp.length - 1; i++){
                //checks for an integer in the array
                //same as the regular expression [0-9]
                if(Character.isDigit(temp[i].charAt(0)) && i + 2 <= temp.length - 1){
                    //makes sure the double digit is actually only a double digit and a triple or more
                    if (temp[i + 1].equals(temp[i]) && !temp[i + 2].equals(temp[i + 1])) {
                        count++;
                        i++;
                    } else {
                        //finds where the repeating characters end
                        for (int j = i; j < temp.length; j++) {
                            if (!temp[j].equals(temp[i])) {
                                if (j != i + 1) {
                                    i = j;
                                }
                                break;
                            }
                        }
                    }
                } //checks second to last position for a double
                else if(Character.isDigit(temp[i].charAt(0)) && i == temp.length - 2){
                    if(temp[i + 1].equals(temp[i])){
                        count++;
                        break;
                    }
                }
            }
        return count;
    }

    @Override
    public boolean isValidVCUEmail() {
        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();
        //separates the string by an @ symbol
        //String[] temp = input.split("@");
        ArrayList<String> tempList = new ArrayList<String>();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '@'){
                tempList.add(tempBuilder.toString());
                tempBuilder.delete(0,tempBuilder.length());
            }
            else{
                tempBuilder.append(input.charAt(i));
            }
        }

        tempList.add(tempBuilder.toString());
        tempBuilder.delete(0,tempBuilder.length());

        /*if the length is more than 1 then the string had an @ symbol it fails the boolean
        this line also checks if there was anything before the @ symbol*/
        if(tempList.size() == 2 && tempList.get(0).length() > 0){
            //builder grabs the end of the email
            tempBuilder.append(tempList.get(tempList.size() - 1));
            tempList.clear();
            //this loop is similar to
            //String[] temp = builder.toString().split("\\.");
            for(int i = 0; i < tempBuilder.length(); i++){
                if(tempBuilder.charAt(i) == '.'){
                    tempList.add(builder.toString());
                    builder.delete(0,builder.length());
                }
                else{
                    builder.append(tempBuilder.charAt(i));
                }
            }

            tempList.add(builder.toString());
            builder.delete(0,builder.length());

            //splits the result based on the number of objects in the array because of the .split("\\.")
            if(tempList.size() == 2){
                return tempList.get(0).equalsIgnoreCase("vcu") && tempList.get(1).equalsIgnoreCase("edu");
            }
            else if(tempList.size() == 3){
                return tempList.get(0).equalsIgnoreCase("mymail") && tempList.get(1).equalsIgnoreCase("vcu") && tempList.get(2).equalsIgnoreCase("edu");
            }
        }
        return false;
    }

    //iterates through the original string and appends only the digits to the string builder
    @Override
    public String standardizePhoneNumber() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            //regular expression would be helpful here too
            if(Character.isDigit(input.charAt(i))){
                builder.append(input.charAt(i));
            }
        }
        //formats the string after checking its size
        if(builder.length() > 0){
            Long temp = Long.parseLong(builder.toString());
            if(temp.toString().length() != 10){
                return "This WackyString is not a phone number.";
            }
            else{
                return "(" + temp.toString().substring(0,3) + ") " + temp.toString().substring(3,6) + "-" + temp.toString().substring(6);
            }
        }
        //invalid string
        return "This WackyString is not a phone number.";
    }

    @Override
    public void ramifyString() {
        StringBuilder builder = new StringBuilder(input);
        for(int i = 0; i < builder.length(); i++){
            if((builder.charAt(i) == '0')){
                //makes sure the double 00 is actually only 2 and not more
                if((i + 2 <= builder.length() - 1 && builder.charAt(i+1) == builder.charAt(i) && builder.charAt(i+2) != builder.charAt(i + 1))
                        || (i == builder.length() - 2 && builder.length() >= 3 && builder.charAt(i) == builder.charAt(i + 1) && builder.charAt(i) != builder.charAt(i - 1))
                        || (builder.length() == 2 && i == 0 && builder.charAt(i) == builder.charAt(i + 1))){
                    //replaces relevant characters
                    builder.replace(i,i+2, "CS@VCU");
                }
                else if((builder.length() == 1) ||(i + 1 <= builder.length() - 1
                        && builder.charAt(i+1) != builder.charAt(i))
                        || (i == builder.length() - 1 && builder.charAt(i) != builder.charAt(i - 1))){
                    //checks before and after the position for another 0
                    builder.replace(i,i + 1, "Go Rams");
                }
                else{
                    //finds where the repeating characters end
                    for(int j = i; j < builder.length(); j++) {
                        if(builder.charAt(j) != builder.charAt(i)){
                            if (j != i + 1) {
                                i = j;
                            }
                            break;
                        }
                    }
                }
            }
        }
        input = builder.toString();
    }

    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException {
        //checks for MyIndexOutOfBoundsException
        if(startPosition < 1 || startPosition > input.length() || endPosition < 1 || endPosition > input.length()){
            throw new MyIndexOutOfBoundsException("Index is outside of string range");
        }
        //checks for IllegalArgumentException after MyIndexOutOfBoundsException is ruled out
        if(startPosition > endPosition){
            throw new IllegalArgumentException("Start position cannot be larger than endPosition");
        }
        //initializes a builder to create the new string
        StringBuilder builder = new StringBuilder();
        if(startPosition > 1){
            //if the start position is in the middle of the string it appends up to that point
                builder.append(input.substring(0,startPosition - 1));
            }
            //loops through the range and swaps each relevant number except 0
            for(int i = startPosition - 1; i < endPosition; i++){

                    switch(input.charAt(i)){
                        case '1':
                            builder.append('I');
                            break;
                        case '2':
                            builder.append("II");
                            break;
                        case '3':
                            builder.append("III");
                            break;
                        case '4':
                            builder.append("IV");
                            break;
                        case '5':
                            builder.append("V");
                            break;
                        case '6':
                            builder.append("VI");
                            break;
                        case '7':
                            builder.append("VII");
                            break;
                        case '8':
                            builder.append("VIII");
                            break;
                        case '9':
                            builder.append("IX");
                            break;
                        //if the range has no numbers then the default case handles appending them
                        default: builder.append(input.charAt(i));
                    }
                    /*if the endPosition is before the end of the input string,
                    this appends the rest of the string on the builder*/
                    if(endPosition < input.length() && i == endPosition - 1){
                        builder.append(input.substring(endPosition));
                    }
            }

            input = builder.toString();
    }
}
