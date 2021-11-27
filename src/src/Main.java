import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.time.*;
public class Main
{
    public static void printF(){
        String functions="1. Add book\n " +
                "2. Search book information\n" +
                "3. Update book information\n" +
                "4. Add borrower\n" +
                "5. Add Librarian\n" +
                "6. Register borrow\n" +
                "7. Register return";
    }
    public static void functions(Statement stat,int choice) throws SQLException {
        Scanner input=new Scanner(System.in);
        if(choice==1){
            System.out.println("You have selected to add a new book.");
            System.out.println("Enter the book id in the following format\n" +
                    "FYYIIII, where F represents the floor, YY is the last two digits " +
                    "of the current year, and IIII is the index of the book");
            String book_id= input.nextLine();
            System.out.println("Enter the title of the book:");
            String title=input.nextLine();

            System.out.println("Enter the Primary Author last name:");
            String lastname=input.nextLine();

            System.out.println("Enter the Primary Author first name:");
            String firstname=input.nextLine();

            System.out.println("Enter the names of other authors, in single entry");
            String otherAuthors=input.nextLine();

            System.out.println("Enter the genre of the book");
            String genre=input.nextLine();
            String formating="INSERT INTO books VALUES("+book_id+","+"\'"+title+"\'"+","+"\'"+lastname+"\'"+","+"\'"+firstname+"\'"+","+"\'"+otherAuthors+"\'"+","+"\'"+genre+"\'"+",1,NULL)";
            //System.out.println(formating);
            stat.executeUpdate(formating);

        }
        else if (choice==2){
            System.out.println("You can search book information based on Title of the book, Primary author last name, and Primary Author first name");
            System.out.println("Which keyword would you like to search for, 1 for title,2 for last name of the primary author, 3 for first name of the primary author");
            int userchoice= input.nextInt();

            if (userchoice==1){
                System.out.println("You have selected to search by title of the book");
                System.out.println("Enter the title of the book");
                String titlec=input.nextLine();
                titlec+=input.nextLine();
                input.close();

                String format="SELECT * FROM books WHERE title="+"\'"+titlec+"\'";
                //System.out.println(format);
                ResultSet result=stat.executeQuery(format);
                //System.out.println(result.toString());
                ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

                int columnsNumber = rsmd.getColumnCount();

                //System.out.println(result);
                //System.out.println(result.getString(2));
                while(result.next()){
                    for(int i = 1 ; i <= columnsNumber; i++){

                        System.out.print("|"+result.getString(i) + " |, "); //Print one element of a row

                    }

                    System.out.println();//Move to the next line to print the next row.
                }
            }
            else if (userchoice==2){
                System.out.println("You have selected to search by last name of the author");
                System.out.println("Enter the last name of the author");
                String name=input.nextLine();
                name+=input.nextLine();
                input.close();

                String format="SELECT * FROM books WHERE author_lastn="+"\'"+name+"\'";
                //System.out.println(format);
                ResultSet result=stat.executeQuery(format);
                //System.out.println(result.toString());
                ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

                int columnsNumber = rsmd.getColumnCount();

                //System.out.println(result);
                //System.out.println(result.getString(2));
                while(result.next()){
                    for(int i = 1 ; i <= columnsNumber; i++){

                        System.out.print("|"+result.getString(i) + " |, "); //Print one element of a row

                    }

                    System.out.println();//Move to the next line to print the next row.
                }
            }
            else if (userchoice==3){
                System.out.println("You have selected to search by first name of the author");
                System.out.println("Enter the fist name of the author");
                String name=input.next();
                name+=input.nextLine();
                input.close();

                String format="SELECT * FROM books WHERE author_first="+"\'"+name+"\'";
                //System.out.println(format);
                ResultSet result=stat.executeQuery(format);
                //System.out.println(result.toString());
                ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

                int columnsNumber = rsmd.getColumnCount();

                //System.out.println(result);
                //System.out.println(result.getString(2));
                while(result.next()){
                    for(int i = 1 ; i <= columnsNumber; i++){

                        System.out.print("|"+result.getString(i) + " |, "); //Print one element of a row

                    }

                    System.out.println();//Move to the next line to print the next row.
                }
            }
            else{
                System.out.println("The choice you have entered is not listed.");
            }
        }
        else if (choice==3){
            System.out.println("What book information would you like to update?");
            System.out.println("Select enter the full name of the book that you want to update ");
            String title="";
            title=input.nextLine();
            //title+=input.nextLine();
            //input.close();

            System.out.println("You have selected to update "+title);
            System.out.println("Select the following information to be updated: 1. Title, 2 primary author  name, 3 others' names ");
            int userchoice= input.nextInt();

            if (userchoice==1){
                System.out.println("Enter the new title");
                String newTitle=input.nextLine();
                newTitle+=input.nextLine();
                input.close();

                String format="UPDATE books SET title="+"\'"+newTitle+"\' WHERE title="+"\'"+title+"\'";
                stat.executeUpdate(format);
                System.out.println("The old title \""+title+"\" has been changed to the new title \""+newTitle+"\"");
            }else if(userchoice==2){
                System.out.println("Enter the new last name of the author");
                String newlstn=input.nextLine();
                newlstn+=input.nextLine();


                System.out.println("Enter the new first name of the author");
                String newlftn=input.nextLine();
                input.close();

                String format="UPDATE books SET author_lastn="+"\'"+newlstn+"\', author_first=\'"+newlftn+"\' WHERE title="+"\'"+title+"\'";
                stat.executeUpdate(format);
                System.out.println("The Primary author's name has been changed to  \""+newlftn+" "+newlstn+"\"");
            }else if(userchoice==3){
                System.out.println("Enter the new other names in a single string");
                String newon=input.nextLine();
                newon+=input.nextLine();
                input.close();
                String format="UPDATE books SET other_auth="+"\'"+newon+"\' WHERE title="+"\'"+title+"\'";
                stat.executeUpdate(format);
                System.out.println("The other authors' names has been changed to  \""+newon+"\" in the book "+title);
            }else{
                System.out.println("The entered choice is not in the option");
            }

        }
        else if (choice==4){
            System.out.println("You are now adding a new borrower");

            String format="SELECT borrower_id FROM borrowers ORDER BY borrower_id DESC LIMIT 1";
            ResultSet resultSet= stat.executeQuery(format);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            while(resultSet.next()){
                for(int i = 1 ; i <= columnsNumber; i++){

                    System.out.print("For reference: Last Borrower Id= "+resultSet.getString(i) ); //Print one element of a row

                }

                System.out.println();//Move to the next line to print the next row.


            }

            System.out.println("");
            System.out.println("Enter the Borrower id in the format of MMDDYYYYHHMM, \nwhere the first M is the current month" +
                    "the D is the current day, Y is the current year, \nH is the current hour in 24 hour format, the Last M is" +
                    "the current minutes:");
            String id="B"+input.nextLine();

            System.out.println("Enter the first name of the new Borrower.");
            String firstn= input.nextLine();

            System.out.println("Enter the last name of the new Borrower.");
            String lastn= input.nextLine();

            System.out.println("Enter the 10 digit phone number of the Borrower");
            String pnum= input.nextLine();
            while(pnum.length()!= 10){
                System.out.println("The entered phone number is does not have length of 10 digits");
                System.out.println("Enter the 10 digit phone number again");
                pnum=input.nextLine();
            }
            String inputFormat="INSERT INTO borrowers VALUES(\'"+id+"\',\'"+lastn+"\',\'"+firstn+"\',\'"+pnum+"\' )";
            stat.executeUpdate(inputFormat);
            System.out.println("New borrower added. Name: "+firstn+" "+lastn+" phone number: "+pnum+" Borrower_id: "+id);
        }

        else if (choice==5){
            System.out.println("You are now adding a new librian");
            // get the last librarian id in the table to use the format as reference
            String format="SELECT librarian_id FROM librarian ORDER BY librarian_id DESC LIMIT 1";
            ResultSet resultSet= stat.executeQuery(format);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            while(resultSet.next()){
                for(int i = 1 ; i <= columnsNumber; i++) {

                    System.out.print("For reference: Last Librarian Id= " + resultSet.getString(i)); //Print one element of a row

                }
                System.out.println();//Move to the next line to print the next row.
            }

            System.out.println("");
            System.out.println("Enter the librarian id in the format of MMDDYYYYHHMM, \nwhere the first M is the current month" +
                    "the D is the current day, Y is the current year, \nH is the current hour in 24 hour format, the Last M is" +
                    "the current minutes:");
            String id="L"+input.nextLine();

            System.out.println("Enter the first name of the new Librarian.");
            String firstn= input.nextLine();

            System.out.println("Enter the last name of the new Librarian.");
            String lastn= input.nextLine();

            System.out.println("Enter the 10 digit phone number of the Librarian");
            String pnum= input.nextLine();
            while(pnum.length()!= 10){
                System.out.println("The entered phone number is does not have length of 10 digits");
                System.out.println("Enter the 10 digit phone number again");
                pnum=input.nextLine();
            }
            System.out.println("Enter the 6 digit password for Librarian");
            String psw= input.nextLine();
            while(psw.length()!= 6){
                System.out.println("The entered phone number is does not have length of 10 digits");
                System.out.println("Enter the 10 digit phone number again");
                psw=input.nextLine();
            }

            System.out.println("Enter the salary for Librarian");
            String salary= input.nextLine();

            String inputFormat="INSERT INTO librarian VALUES(\'"+id+"\',\'"+firstn+"\',\'"+lastn+"\',\'"+pnum+"\',"+"\'"+psw+"\',"+salary+")";
            stat.executeUpdate(inputFormat);
            System.out.println("New borrower added. Name: "+firstn+" "+lastn+" phone number: "+pnum+" Borrower_id: "+id);

        }

        else if (choice==6){
            // Register Borrow
            // need to fill in the borrow_lst table in the database with
            // borrow list id , borrower id, borrow date, expected return date(usually 1 month), if returned,
            // actual return date, and librarian id that conducted the borrow
            System.out.println("This options allows you to register borrows");
            System.out.println("Borrows can only be done on books that are in the system and physically in the library.\n");


            // asking to enter the title of the book that is being borrowed
            System.out.print("Enter the title of the book that is being borrowed:");
            String titile=input.nextLine();
            System.out.println("");

            //check if the book is in the system and available
            String checking="SELECT avail FROM books WHERE title=\'"+titile+"\'";
            ResultSet resultSet= stat.executeQuery(checking);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String avil="";
            boolean inS=false; // if the book is in the system it will be true
            // print the result

            while(resultSet.next()){
                inS=true; // in here means the book is in the system
                for(int i = 1 ; i <= columnsNumber; i++){
                    avil=resultSet.getString(i);
                    System.out.println("For reference: Availability Id= "+resultSet.getString(i)+", \nif 1 is shown " +
                            "the book is available, if other then book is not available.\n" ); //Print one element of a row
                }

            }

            if (avil.equals("0") || !(inS)){
                System.out.println("The book: "+titile+" is either not available or not in the system.");

            }
            if (avil.equals("1")){
                // when avil equals to 1, it means the book is in the library database and physically in the library
                System.out.println("Please enter the new borrow_lst_id,\n" +
                        "The format of the Borrow list id is the same as other ids, \n" +
                        "MMDDYYYYHHMM, with month day year Hour and minute format:");
                String id= "BLST"+input.nextLine();

                System.out.println(" Enter the Borrower id: (if need to check borrower id,\n" +
                        " ask for first and last name of the borrower and enter 1) otherwise enter 0");
                int uCBid=input.nextInt();

                // the reponse of ther user
                String brid="";
                if (uCBid==1){
                    // gets the phone number of the borrower to check for the borrower id in the system
                    // phone number has more unique characteristics
                    // since no two people would have the same phone number
                    System.out.println("Enter the phone number");
                    String pnum=input.next();

                    String ckBid="SELECT borrower_id FROM borrowers WHERE phone_num=\""+pnum +"\" ";
                    // send to the database
                    ResultSet result= stat.executeQuery(ckBid);
                    ResultSetMetaData rsmds = (ResultSetMetaData) result.getMetaData();
                    int columnsNumbers = rsmds.getColumnCount();
                    // print the result
                    while(result.next()){
                        for(int i = 1 ; i <= columnsNumbers; i++){
                            brid=result.getString(i); // Stores the borrower id
                            System.out.println("For reference: Borrower Id= "+result.getString(i)); //Print one element of a row
                        }

                    }

                 //END of the IF
                }
                else if (uCBid==0){
                    // when the user knows the borrower id
                    System.out.println("Enter the Borrower_id");
                    brid=input.nextLine();
                    String ckBid="SELECT * FROM borrowers WHERE borrower_id=\""+brid +"\" ";
                    ResultSet result= stat.executeQuery(ckBid);
                    if( !(result.next())){
                        System.out.println("Invalid Borrower id.");
                    }
                    //end of else if
                }
                // setting the borrow date and expected return dates
                String brdate= LocalDate.now().toString();
                System.out.println("Borrow Date: "+brdate);
                String erdate=LocalDate.now().plusMonths(1).toString();
                System.out.println("Expected return date"+erdate);

                int retuned=0; // if returned the value would be 1, otherwise 0
                String areturndate="null";

                System.out.println("\nEnter Librarian phone number");
                String lbph_num=input.next();
                String ckBid="SELECT librarian_id FROM librarian WHERE phone_num=\""+lbph_num +"\" ";
                // send to the database
                ResultSet result= stat.executeQuery(ckBid);
                ResultSetMetaData rsmds = (ResultSetMetaData) result.getMetaData();
                int columnsNumbers = rsmds.getColumnCount();
                // create variable to store librarian id
                String lbid="";
                // print the result
                while(result.next()){
                    for(int i = 1 ; i <= columnsNumbers; i++){
                        lbid=result.getString(i); // Stores the borrower id
                        System.out.println("For reference: Librian Id= "+result.getString(i)); //Print one element of a row
                    }

                }
                String regformat="INSERT INTO borrow_lst VALUES (\'"+id+"\',\'"+brid+
                        "\',\'"+brdate+"\',\'"+erdate+"\',\'"+retuned+"\',"+areturndate+",\'"
                        +lbid+"\')";
                //System.out.println(regformat);
                String bokformat="UPDATE books SET avail=0, borrow_lst_id=\'"+id+"\' WHERE title=\'"+titile+"\'";
                //System.out.println(bokformat);

                // Update the database
                stat.executeUpdate(regformat);
                stat.executeUpdate(bokformat);
                System.out.println("Borrow registered under borrow list id of "+id+" for the book "+titile);
            }
            //END OF OPTION 6
        }
        else if (choice==7){

        }
    }

    public static void main(String[] args){

        Scanner input=new Scanner(System.in);

        /*System.out.println("Enter database URL: ");
        String dbURL=input.next();

        System.out.println("Enter database username: ");
        String dbusername=input.next();

        System.out.println("Enter database password");
        String dbpasw=input.next();

        System.out.println("database URL"+dbURL +" "+"database username: "+dbusername+" "+"database password"+""+dbpasw);
        */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","09062000");

            Statement statement =connection.createStatement();

            /*ResultSet resultSet=statement.executeQuery("select * from books");

            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            while(resultSet.next()){
                for(int i = 1 ; i <= columnsNumber; i++){

                    System.out.print("|"+resultSet.getString(i) + " |, "); //Print one element of a row

                }

                System.out.println();//Move to the next line to print the next row.


            }*/
            functions(statement,6);



        } catch (Exception e){
            e.printStackTrace();
        }


    }
}