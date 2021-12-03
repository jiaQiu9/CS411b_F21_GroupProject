/**
 * Authors: CS 411 section b1 Group 2
 *
 * Description for the system: This is a Library management system, where the librarian could register new book,
 * search for boo information, update book information, add new borrower, add new librarian, register borrow, register
 * return, and stop the service.
 *
 * Requirements: Java 15, mysql 8.0.27
 *
 * Other comments: most of the functions are just requests to the mysql repository,
 *                  The user must import the database from the database folder into their mysql database,
 *                  If needed, the user must change the connection to the database.
 *
 * Contact: jiaq9@bu.edu, if any questions is raised.
 * **/

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.time.*;
public class Main
{
    public static void printF(){
        String functions="1. Add book\n" +
                "2. Search book information\n" +
                "3. Update book information\n" +
                "4. Add borrower\n" +
                "5. Add Librarian\n" +
                "6. Register borrow\n" +
                "7. Register return\n"+
                "8. To end the service";
        System.out.println(functions);
    }
    public static void main(String[] args){

        //Scanner input=new Scanner(System.in);
        System.out.println("You need to connect to a mysql database, before using the service");
        System.out.println("");


        // the following commented out lines of code were meant to ask the user for connection credential to their
        // database,


        /*System.out.println("Enter database URL: ");
        String dbURL=input.next();

        System.out.println("Enter database username: ");
        String dbusername=input.next();

        System.out.println("Enter database password");
        String dbpasw=input.next();

        System.out.println("database URL"+dbURL +" "+"database username: "+dbusername+" "+"database password"+""+dbpasw);
        */
        try {
            // Connecting to mysql server
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","09062000");

            Statement statement =connection.createStatement();

            // starting the service of this software/ program
            Scanner input=new Scanner(System.in);
            while(true){
                // the while loop that allows the user to continuely apply functions, until they choose to end
                // the service
                printF();
                System.out.print("\nPlease select the service that you want to conduct:\n");
                int choice=input.nextInt();

                System.out.println("\n");
                if(choice==8){
                    System.out.println("Now the service will be shutting off");
                    break;
                }
                else if (choice==1 || choice==2|| choice==3|| choice==4 || choice==5|| choice==6 || choice==7 ){
                    LibraryRepository.userActions(statement,choice); // calling the functions and applying the user's choice
                }
                System.out.println("Please make another choice\n\n");
            }
            System.out.println("System shutting down.");

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}