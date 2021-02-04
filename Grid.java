/**********************************************************************************
 * Grid.java
 * *********************************************************************************
 * Project 1: tic tac toe grid class, sets up the methods for a game of tic tac toe
 * CMSC 256-901
 * 2/4/2021
 * Gavin Cutchin
 **********************************************************************************/
package cmsc256;

public class Grid {
    //constant variable to dictate grid size
    private final int SIZE = 3;
    private char[][] ticTacToeGrid = new char[SIZE][SIZE];
    /*
     * Default constructor that initializes a 3 by 3 grid to default char values ('\u0000')
     * */
    public Grid() {
        for (char[] innerArray: ticTacToeGrid) {
            for (char k: innerArray) {
                k = '\u0000';
            }
        }
    }


    /**
     * Formats the grid row to a String that consists of a space, the char,
     *  a space, a vertical pipe, a space, the char, a space, a vertical pipe,
     * a space, the char, and a final space,
     * for example: " X | X | X "
     *
     * @param rowIndex  the index of the row to convert to a String
     * @return a formatted String representation of the row
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public String getRow(int rowIndex) throws IllegalArgumentException{
        //checks if input is within the grid row range
        if(rowIndex > -1 && rowIndex < SIZE){
            String temp = " ";

            //this loop stops one short so the formatting doesn't go past the last character
            for (int i = 0; i < SIZE -1; i++) {
                if(ticTacToeGrid[rowIndex][i] == '\u0000'){
                    temp+= "  | ";
                }
                else{
                    temp += ticTacToeGrid[rowIndex][i] + " | ";
                }
            }
            //concatenates the last character
            if(ticTacToeGrid[rowIndex][SIZE -1] == '\u0000'){
                temp+= "  ";
            }
            else{
                temp += ticTacToeGrid[rowIndex][SIZE - 1] + " ";
            }
            return temp;
        }
        else{
            //throws an exception for the row index
            throw new IllegalArgumentException("Invalid row index.");
        }
    }

    /**
     * Sets the grid location to the given value
     * @param value         char value for the grid location
     * @param rowIndex      the index of the row position
     * @param columnIndex   the index of the column position
     * @throws IllegalArgumentException if the row index or column index is invalid
     *                                  or if the position is not null
     */
    public void setPosition(char value, int rowIndex, int columnIndex) throws IllegalArgumentException{
        //if statement checks in the appropriate order if value, rowIndex, and columnIndex are acceptable and that the space is 'empty'
        if(checkInput(value) && rowIndex < SIZE &&
                rowIndex > -1 && columnIndex > -1 &&
                columnIndex < SIZE &&
                ticTacToeGrid[rowIndex][columnIndex] == '\u0000'){
            ticTacToeGrid[rowIndex][columnIndex] = value;
        }
        else{
            /*
            throws an exception for any invalid input(value, rowIndex, or columnIndex)
            could potentially be split up to clarify exception reasoning rather than just "Incorrect position information"
             */
            throw new IllegalArgumentException("Incorrect position information.");
        }
    }

    /**
     * Checks for valid input value
     * @param inputValue the char value to be checked
     * @return true if input value is X, x, O, or o
     * @throws IllegalArgumentException if character is not X or O
     */
    public boolean checkInput(char inputValue) throws IllegalArgumentException{
        //if statement checks if the input is of the any 4 character values
        if(inputValue == 'X' || inputValue == 'x' || inputValue == 'O' || inputValue == 'o'){
            return true;
        }
        else{
            //throws an exception if the input's neither an X or O
            throw new IllegalArgumentException();
        }

    }

    /**
     * Checks if all positions have a char value
     * @return true if none of the grid locations contain the null character ('\u0000')
     */
    public boolean isFull(){
        //loops through each 1D array in ticTacToeGrid
        for (char[] row: ticTacToeGrid) {
            //loops through each character in each row in the ticTacToeGrid
            for(char i: row){
                if(i == '\u0000'){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  Check if row has all the same characters
     * @param rowIndex  the row index to check
     * @return  true if row contains the same char value
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public boolean isRowMatching(int rowIndex) throws IllegalArgumentException{
        //checks if rowIndex is acceptable
        if(rowIndex > -1 && rowIndex < SIZE){
            //checks if the given row matches all the way across from positions [rowIndex][0-2]
           if(ticTacToeGrid[rowIndex][0] == ticTacToeGrid[rowIndex][1] &&
                   ticTacToeGrid[rowIndex][0] == ticTacToeGrid[rowIndex][2]){
               return true;
           }
            return false;
        }
        else{
            //throws an exception if the row given is invalid
            throw new IllegalArgumentException("Invalid row index.");
        }
    }

    /**
     * Check if column has all the same characters
     * @param columnIndex   the column index to check
     * @return  true if column contains the same char value
     * @throws IllegalArgumentException if an invalid column index is given
     */
    public boolean isColumnMatching(int columnIndex) throws IllegalArgumentException{
        //checks if the columnIndex is acceptable
        if(columnIndex > -1 && columnIndex < SIZE){
            //checks if each character in the column is the same down the colume [0-2][columnIndex]
            if(ticTacToeGrid[0][columnIndex] == ticTacToeGrid[1][columnIndex] &&
                    ticTacToeGrid[0][columnIndex] == ticTacToeGrid[2][columnIndex]){
                return true;
            }
            return false;
        }
        else{
            //throws an exception if the columnIndex was unacceptable
            throw new IllegalArgumentException("Invalid column index.");
        }
    }

    /**
     * Checks if either diagonal has the same characters
     * @return true if grid position 0,0; 1,1; and 2,2 are the same
     *              if grid position 2,0; 1,1; and 0,2 are the same
     */
    public boolean hasDiagonalMatch(){
        //checks if the diagonals of the grid are equal, the top-left to bottom-right first then vice versa
        if(ticTacToeGrid[0][0] == ticTacToeGrid[1][1] && ticTacToeGrid[0][0] == ticTacToeGrid[2][2] ||
                ticTacToeGrid[2][0] == ticTacToeGrid[1][1] && ticTacToeGrid[2][0] == ticTacToeGrid[0][2]){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if there is a character with three in a row on the grid
     * @return winning character if there is a row, column or diagonal match
     *          otherwise returns the null character
     */
    public char checkForWinner(){
        for(int i = 0; i < SIZE; i++){
            //checks a row on each loop
            if(isRowMatching(i)){
                return ticTacToeGrid[i][0];
            }

            //checks a column on each loop
            if(isColumnMatching(i)){
                return ticTacToeGrid[0][i];
            }
        }

        //checks for diagonal otherwise
        if(hasDiagonalMatch()){
            return ticTacToeGrid[1][1];
        }
        //no matches to return
        return '\u0000';
    }

    @Override
    /**
     * Returns a string representation of the grid with each row separated by a line
     * @return string
     */
    public String toString() {
        return getRow(0) + "\n---------\n" + getRow(1) + "\n---------\n" + getRow(2) + "\n";
    }
}