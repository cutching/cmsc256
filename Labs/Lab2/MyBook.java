/****************************************************************************
 *MyBook.java
 ****************************************************************************
 *Defining the methods of the MyBook Object
 *Gavin Cutchin
 *Benjamin Yiu
 *2/2/2021
 *CMSC 256 901
 **********************************************************/

package cmsc256;
import java.util.Objects;

public class MyBook implements Comparable<MyBook>{
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    public MyBook(){
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }

    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13){
        setTitle(title);
        setAuthorFirstName(authorFirstName);
        setAuthorLastName(authorLastName);
        setISBN10(ISBN10);
        setISBN13(ISBN13);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException{
        if(title != null){
            this.title = title;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) throws IllegalArgumentException {
        if(authorFirstName != null){
            this.authorFirstName = authorFirstName;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) throws IllegalArgumentException{
        if(authorLastName != null){
            this.authorLastName = authorLastName;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) throws IllegalArgumentException{
        if(ISBN10.length() == 10 && ISBN10.matches("[0-9]+")){
            this.ISBN10 = ISBN10;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getISBN13() throws IllegalArgumentException{
        return ISBN13;
    }

    public void setISBN13(String ISBN13) throws IllegalArgumentException{
        if(ISBN13.length() == 13 && ISBN13.matches("[0-9]+")){
            this.ISBN13 = ISBN13;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString(){
        return "Title: " + getTitle() + "\n" +
                "Author: " + getAuthorFirstName() + " "
                           + getAuthorLastName() + "\n"
                + "ISBN10: " + getISBN10() + "\n"
                + "ISBN13: " + getISBN13();
    }

    @Override
    public boolean equals(Object otherBook) {
        if (this == otherBook) {
            return true;
        }

        if (!(otherBook instanceof MyBook)) {
            return false;
        }

        MyBook myBook = (MyBook)otherBook;
        return Objects.equals(ISBN10, myBook.ISBN10) &&
                Objects.equals(ISBN13, myBook.ISBN13);
    }

    public int compareTo(MyBook other){
        int result = 0;

        if(!authorLastName.equals(other.getAuthorLastName())){
            result = authorLastName.compareTo(other.getAuthorLastName());
        }

        if(result == 0 && !authorFirstName.equals(other.getAuthorFirstName())){
            result = authorFirstName.compareTo(other.getAuthorFirstName());
        }

        if(result == 0 && !title.equals(other.getTitle())){
            result = title.compareTo(other.getTitle());
        }

        return  result;
    }

}
