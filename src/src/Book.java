// Authors: CS 411 b F21 Group 2
// The attribute file for the books in the libary
// each book will have a name, author name, publisher name, and publishing year

import java.io.*;
import java.util.*;


public class Book {

    private String bookID; // ID indexing for library placement
    private String title;  // the title of the book
    private String author; // author of the books, for multiple author books
                            // it will just store the authors in one string
                            // separated by a semicolon
    private String Genre; // the genre of the book
    private boolean available;  // True for in library; False for borrowed
    //private ArrayList<BorrowRecord> BorrowRecord; // record every person who borrowed this book

    public Book(String bid, String tit, String auth, String gen, boolean avail){
        bookID=bid;
        title=tit;
        author=auth;
        Genre=gen;
        available=avail;

    }

    // printing book information
    public void printInfo(){
        System.out.println("Title: "+title+"\t"+"Author"+author+"\t"+"Genre: "+Genre+"\t Availability: "+available);
    }

    // chainging book information
    public void changeBookInfo() throws IOException {
        Scanner scanner=new Scanner(System.in);
        String input;

        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

        //asking if the user wants to change the author
        System.out.println("Update Author? (y/n)\n");
        input =scanner.next();

        if(input.equals("y")){
            System.out.println("Enter name of new Author: ");
            author= reader.readLine();
        }

        //asking the user is they want to change the genre of the book
        System.out.println("Update Genre? (y/n)\n");
        input=scanner.next();

        if(input.equals("y")){
            System.out.println("Enter new genre: ");
            Genre=reader.readLine();

        }

        // Asking the user if they want to change the title of the book

        System.out.println("Update title? (y/n)");
        input=scanner.next();

        if (input.equals("y")){
            System.out.println("Enter new title: ");
            title=reader.readLine();
        }

        System.out.println(" The book is updated with the new information");
    }

    // getter function for the books
    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return Genre;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable(){
        return available;
    }

    public String getBookID(){
        return bookID;
    }

    //setter function
    public void setAvailable(boolean s){
        available=s;
    }

    public void setBookID(String loc, int index){
        bookID=loc + Integer.toString(index);
    }

    public void setAuthor(String auth){
        author=auth;
    }

    public void setTitle(String tit){
        title=tit;
    }

    public void setGenre(String gen){
        Genre=gen;
    }


}