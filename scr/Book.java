// Authors: CS 411 b F21 Group 2
// The attribute file for the books in the libary
// each book will have a name, author name, publisher name, and publishing year
public class Book {
    private int bookID; // ID indexing for library placement
    private String title;  // the title of the book
    private String author; // author of the books, for multiple author books
                            // it will just store the authors in one string
                            // separated by a semicolon
    private String Genre; // the genre of the book
    private boolean available;  // True for in library; False for borrowed
    private ArrayList<BorrowRecord> BorrowRecord; // record every person who borrowed this book

    public Book(int bid, String tit, String auth, String gen, boolean avail){
        bookID=bid;
        title=tit;
        author=auth;
        Genre=gen;
        available=avail;

    }


}