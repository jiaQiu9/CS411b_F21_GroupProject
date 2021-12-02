import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryRepository {
    public static void userActions(Statement stat, int choice) throws SQLException {
        Scanner input = new Scanner(System.in);
        if (choice == 1) {
            // OPTIONS 1
            // Adding or registering a new book fucntionality
            System.out.println("You have selected to add a new book.");

            String book_id;
            String ckId;
            ResultSet resultSet;
            // This loop will first check if the book_id is in the system or not
            // if in the system it will
            while (true) {
                // need a new book id for the sql table
                System.out.println("Enter the book id in the following format\n" +
                        "FYYIIII, where F represents the floor, YY is the last two digits " +
                        "of the current year, and IIII is the index of the book");
                book_id = input.nextLine();
                ckId = "SELECT * FROM books WHERE book_id=\'" + book_id + "\'";
                resultSet = stat.executeQuery(ckId); // sending command to database
                // if there is a book with this book id in the system, then the resultSet.next() would be true
                // else it will be false
                if (resultSet.next()) {
                    // the book_id is in the system
                    System.out.println("The book_id is in the system, please enter another one");
                    continue;
                } else if (!(resultSet.next())) {
                    if (book_id.length() == 7) {
                        // the book_id is of length 7 and not in the system
                        break;
                    } else {
                        // teh book_id if not of length 7 but it is not in the system
                        System.out.println("The book_id entered is not of right length.");
                        continue;
                    }

                }
            }

            // entering the title of the book
            System.out.println("Enter the title of the book:");
            String title = input.nextLine();

            // Enter the primary author's last name
            System.out.println("Enter the Primary Author last name:");
            String lastname = input.nextLine();

            // Enter the primary author's first name
            System.out.println("Enter the Primary Author first name:");
            String firstname = input.nextLine();

            // enter the full names of other authors, because some books have more than one author
            // and storing them into a single string makes more sense as the number of other authors is
            // not easy to generalise
            System.out.println("Enter the names of other authors, in single entry");
            String otherAuthors = input.nextLine();

            // enter the genre of the book
            System.out.println("Enter the genre of the book");
            String genre = input.nextLine();

            // formating the information into sql command
            String formating = "INSERT INTO books VALUES(" + book_id + "," + "\'" + title + "\'" + "," + "\'" + lastname + "\'" + "," + "\'" + firstname + "\'" + "," + "\'" + otherAuthors + "\'" + "," + "\'" + genre + "\'" + ",1,NULL)";

            // sending the command to the database
            stat.executeUpdate(formating);

        } else if (choice == 2) {
            //OPTION 2
            // search for book information
            System.out.println("You can search book information based on Title of the book, Primary author last name, and Primary Author first name");

            // making the user choose how they will search for book information
            System.out.println("Which keyword would you like to search for, 1 for title,2 for last name of the primary author, 3 for first name of the primary author");
            int userchoice = input.nextInt();
            String format;
            boolean inSys=false; // for storing the state of true (info in the system), false (info not in the system)
            // applying user choice
            if (userchoice == 1) {
                // search book information based on title
                String titlec;
                // asking the user for the title of the book
                System.out.println("You have selected to search by title of the book");
                System.out.println("Enter the title of the book");
                titlec = input.nextLine();
                titlec += input.nextLine();

                // formating the information to be a sql query
                format = "SELECT * FROM books WHERE title=" + "\'" + titlec + "\'";

                ResultSet result = stat.executeQuery(format);


                ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                    //System.out.println("Here 109");
                while (result.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        System.out.print("|" + result.getString(i) + " |, "); //Print one element of a row

                    }

                    System.out.println();//Move to the next line to print the next row.
                    inSys=true;
                }
                if (inSys==false){
                    System.out.println("The book is not in the system");
                }

            } else if (userchoice == 2) {
                // search book information based on primary author's last name
                System.out.println("You have selected to search by last name of the author");
                System.out.println("Enter the last name of the author");
                String name = input.nextLine();
                name += input.nextLine();

                // formating the information into sql query to be executed
                format = "SELECT * FROM books WHERE author_lastn=" + "\'" + name + "\'";

                ResultSet result = stat.executeQuery(format);



                    ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

                    int columnsNumber = rsmd.getColumnCount();
                    //printing the results if there are in the system
                    while (result.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {

                            System.out.print("|" + result.getString(i) + " |, "); //Print one element of a row

                        }

                        System.out.println();//Move to the next line to print the next row.
                        inSys=true; // if in the system, it will modify inSys to true, while it is false when defined
                    }
                if (inSys==false){
                    // is not in the system
                    System.out.println("The author is not in the system");
                }


            } else if (userchoice == 3) {
                // search book information based on the primary author's first name
                System.out.println("You have selected to search by first name of the author");
                System.out.println("Enter the fist name of the author");
                String name = input.next();
                name += input.nextLine();
                //  formatting sql command for later use
                format = "SELECT * FROM books WHERE author_first=" + "\'" + name + "\'";
                ResultSet result = stat.executeQuery(format);


                    ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

                    int columnsNumber = rsmd.getColumnCount();

                    while (result.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {

                            System.out.print("|" + result.getString(i) + " |, "); //Print one element of a row

                        }

                        System.out.println();//Move to the next line to print the next row.
                        inSys=true;
                    }
                if (inSys==false){
                    System.out.println("The author is not in the system");
                }

            } else {
                System.out.println("The choice you have entered is not listed. Or the book is not in the system.");
            }

            // END of 2
        } else if (choice == 3) {
            // OPTION 3
            // update book information

            System.out.println("What book information would you like to update?");
            System.out.println("Enter the full name of the book that you want to update ");
            String title = "";
            title = input.nextLine(); // asking the user for input

            // display the title entered
            System.out.println("You have selected to update " + title);

            // checks if the books is in the database or not, by sending a query to the sql
            String checkTi = "SELECT * FROM books WHERE title=\'" + title + "\'";
            ResultSet resultSet = stat.executeQuery(checkTi);
            // if the resultset.next is true, then the book with that tile is in the system
            // if false, then the book is not.
            if (resultSet.next()) {
                System.out.println("Select the following information to be updated: 1. Title, 2 primary author  name, 3 others' names ");
                // asking the user what they want to update
                int userchoice = input.nextInt();
                // checking the user input with if statements
                if (userchoice == 1) {
                    // for updating book title
                    System.out.println("Enter the new title");
                    String newTitle = input.nextLine();
                    newTitle += input.nextLine();
                    String format = "UPDATE books SET title=" + "\'" + newTitle + "\' WHERE title=" + "\'" + title + "\'";
                    stat.executeUpdate(format);
                    System.out.println("The old title \"" + title + "\" has been changed to the new title \"" + newTitle + "\"");

                } else if (userchoice == 2) {
                    // for updating primary author name, includes both last and first name of the author
                    System.out.println("Enter the new last name of the author");
                    String newlstn = input.nextLine();
                    newlstn += input.nextLine();
                    System.out.println("Enter the new first name of the author");
                    String newlftn = input.nextLine();
                    String format = "UPDATE books SET author_lastn=" + "\'" + newlstn + "\', author_first=\'" + newlftn + "\' WHERE title=" + "\'" + title + "\'";
                    stat.executeUpdate(format);
                    System.out.println("The Primary author's name has been changed to  \"" + newlftn + " " + newlstn + "\"");

                } else if (userchoice == 3) {
                    // updates the names of the other authors
                    System.out.println("Enter the new other names in a single string");
                    String newon = input.nextLine();
                    newon += input.nextLine();

                    String format = "UPDATE books SET other_auth=" + "\'" + newon + "\' WHERE title=" + "\'" + title + "\'";
                    stat.executeUpdate(format);
                    System.out.println("The other authors' names has been changed to  \"" + newon + "\" in the book " + title);
                } else {
                    // if the user entered something other than instructed
                    System.out.println("The entered choice is not in the option");
                }
            } else {
                // if the book is not in  the system
                System.out.println("The book with the title= " + title + " is not in the system.");
            }


        } else if (choice == 4) {
            // OPTION 4
            // Add borrower
            System.out.println("You are now adding a new borrower");

            // printing the last borrower id for reference
            String format = "SELECT borrower_id FROM borrowers ORDER BY borrower_id DESC LIMIT 1";
            ResultSet resultSet = stat.executeQuery(format);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print("For reference: Last Borrower Id= " + resultSet.getString(i)); //Print one element of a row
                }
                System.out.println();//Move to the next line to print the next row.
            }

            // asking the user to enter the borrower id
            String id;

            while (true) {
                System.out.println("");
                System.out.println("Enter the Borrower id in the format of MMDDYYYYHHMM, \nwhere the first M is the current month" +
                        "the D is the current day, Y is the current year, \nH is the current hour in 24 hour format, the Last M is" +
                        "the current minutes:");
                id = "B" + input.nextLine();
                if (id.length() == 13) {
                    format = "SELECT * FROM borrowers WHERE borrower_id=\'" + id + "\'";
                    ResultSet resultSet1 = stat.executeQuery(format);
                    if (resultSet1.next()) {
                        System.out.println("The borrower id, " + id + ", is already in the system, please enter another one");
                        continue;
                    } else {
                        // if the borrower id entered by the user had the right format and is not in the system.
                        break;
                    }
                } else {
                    System.out.println("The borrower id entered is not in the right format.");
                    break;
                }
            }

            // asking for the borrower information
            System.out.println("Enter the first name of the new Borrower.");
            String firstn = input.nextLine();

            System.out.println("Enter the last name of the new Borrower.");
            String lastn = input.nextLine();

            //System.out.println("Enter the 10 digit phone number of the Borrower");
            String pnum;
            // making sure that phone number entered is the right length and not already in the system
            while (true) {
                System.out.println("The entered phone number is does not have length of 10 digits");
                System.out.println("Enter the 10 digit phone number again");
                pnum = input.nextLine();

                // if the phone number is not in the right format
                if (pnum.length() == 10) {
                    // checking if the phone number is in the system or not
                    format = "SELECT * FROM borrowers WHERE phone_num=\'" + pnum + "\'";
                    ResultSet resultSet1 = stat.executeQuery(format);
                    if (resultSet1.next()) {
                        System.out.println("The phone number entered is in the system.\n" +
                                "Please enter another phone number.");
                        continue;
                    } else {
                        System.out.println("The phone number " + pnum + " entered has been accepted ");
                        break;
                    }
                } else {
                    System.out.println(" The Phone number entered is not in the right format.");
                    continue;
                }


            }
            // sending adding borrower to the borrowers table in the database.
            String inputFormat = "INSERT INTO borrowers VALUES(\'" + id + "\',\'" + lastn + "\',\'" + firstn + "\',\'" + pnum + "\' )";
            stat.executeUpdate(inputFormat);
            System.out.println("New borrower added. Name: " + firstn + " " + lastn + " phone number: " + pnum + " Borrower_id: " + id);
        }
        else if (choice == 5) {
            //OPTION 5 Add librarian
            System.out.println("You are now adding a new librian");
            // get the last librarian id in the table to use the format as reference
            String format = "SELECT librarian_id FROM librarian ORDER BY librarian_id DESC LIMIT 1";
            ResultSet resultSet = stat.executeQuery(format);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {

                    System.out.print("For reference: Last Librarian Id= " + resultSet.getString(i)); //Print one element of a row

                }
                System.out.println();//Move to the next line to print the next row.
            }

            System.out.println("");

            String id;

            while (true) {
                System.out.println("");
                System.out.println("Enter the Librarian id in the format of MMDDYYYYHHMM, \nwhere the first M is the current month" +
                        "the D is the current day, Y is the current year, \nH is the current hour in 24 hour format, the Last M is" +
                        "the current minutes:");
                id = "L" + input.nextLine();
                if (id.length() == 13) {
                    format = "SELECT * FROM librarian WHERE librarian_id=\'" + id + "\'";
                    ResultSet resultSet1 = stat.executeQuery(format);
                    if (resultSet1.next()) {
                        System.out.println("The librarian id, " + id + ", is already in the system, please enter another one");
                        continue;
                    } else {
                        // if the borrower id entered by the user had the right format and is not in the system.
                        break;
                    }
                } else {
                    System.out.println("The librarian id entered is not in the right format.");
                    break;
                }
            }

            // asking for the first name of the new librarian
            System.out.println("Enter the first name of the new Librarian.");
            String firstn = input.nextLine();
            // asking for the last name of the new librarian
            System.out.println("Enter the last name of the new Librarian.");
            String lastn = input.nextLine();
            // asking for ten digit phone number of the new librarian
            System.out.println("Enter the 10 digit phone number of the Librarian");
            String pnum;
            // making sure that phone number entered is the right length and not already in the system
            while (true) {
                System.out.println("The entered phone number is does not have length of 10 digits");
                System.out.println("Enter the 10 digit phone number again");
                pnum = input.nextLine();

                // if the phone number is not in the right format
                if (pnum.length() == 10) {
                    // checking if the phone number is in the system or not
                    format = "SELECT * FROM librarian WHERE phone_num=\'" + pnum + "\'";
                    ResultSet resultSet1 = stat.executeQuery(format);
                    if (resultSet1.next()) {
                        System.out.println("The phone number entered is in the system.\n" +
                                "Please enter another phone number.");
                        continue;
                    } else {
                        System.out.println("The phone number " + pnum + " entered has been accepted ");
                        break;
                    }
                } else {
                    System.out.println(" The Phone number entered is not in the right format.");
                    continue;
                }
                // END OF THE PHONE NUMBER ASKING WHILE LOOP
            }

            // asking for the password for the new librarian to access the system.
            System.out.println("Enter the 6 digit password for Librarian");
            String psw = input.nextLine();
            while (psw.length() != 6) {
                System.out.println("The entered password does not have length of 6 digits");
                System.out.println("Enter the 6 digit password again");
                psw = input.nextLine();
            }
            // asking for the salary of the new librarian
            System.out.println("Enter the salary for Librarian");
            String salary = input.nextLine();
            // formatting the sql query
            String inputFormat = "INSERT INTO librarian VALUES(\'" + id + "\',\'" + firstn + "\',\'" + lastn + "\',\'" + pnum + "\'," + "\'" + psw + "\'," + salary + ")";
            stat.executeUpdate(inputFormat);
            System.out.println("New librarian added. Name: " + firstn + " " + lastn + " phone number: " + pnum + " Borrower_id: " + id);

            // END OF OPTION 5
        } else if (choice == 6) {
            // Register Borrow
            // need to fill in the borrow_lst table in the database with
            // borrow list id , borrower id, borrow date, expected return date(usually 1 month), if returned,
            // actual return date, and librarian id that conducted the borrow
            System.out.println("This options allows you to register borrows");
            System.out.println("Borrows can only be done on books that are in the system and physically in the library.\n");


            // asking to enter the title of the book that is being borrowed
            System.out.print("Enter the title of the book that is being borrowed:");
            String titile = input.nextLine();
            System.out.println("");

            //check if the book is in the system and available
            String checking = "SELECT avail FROM books WHERE title=\'" + titile + "\'";
            ResultSet resultSet = stat.executeQuery(checking);
            ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String avil = "";
            boolean inS = false; // if the book is in the system it will be true
            // print the result

            while (resultSet.next()) {
                inS = true; // in here means the book is in the system
                for (int i = 1; i <= columnsNumber; i++) {
                    avil = resultSet.getString(i);
                    System.out.println("For reference: Availability Id= " + resultSet.getString(i) + ", \nif 1 is shown " +
                            "the book is available, if other then book is not available.\n"); //Print one element of a row
                }

            }

            if (avil.equals("0") || !(inS)) {
                // when not availabile or not in the system, it will print the following message
                // and this functionality/ option will end
                System.out.println("The book: " + titile + " is either not available or not in the system.");

            }
            if (avil.equals("1")) {
                // when avil equals to 1, it means the book is in the library database and physically in the library
                System.out.println("Please enter the new borrow_lst_id,\n" +
                        "The format of the Borrow list id is the same as other ids, \n" +
                        "MMDDYYYYHHMM, with month day year Hour and minute format:");
                String id = "BLST" + input.nextLine();

                System.out.println(" Enter the Borrower id: (if need to check borrower id,\n" +
                        " ask for first and last name of the borrower and enter 1) otherwise enter 0");
                int uCBid = input.nextInt();

                // the response of their user
                String brid = "";
                if (uCBid == 1) {
                    // gets the phone number of the borrower to check for the borrower id in the system
                    // phone number has more unique characteristics
                    // since no two people would have the same phone number
                    System.out.println("Enter the phone number");
                    String pnum = input.next();

                    String ckBid = "SELECT borrower_id FROM borrowers WHERE phone_num=\"" + pnum + "\" ";
                    // send to the database
                    ResultSet result = stat.executeQuery(ckBid);
                    ResultSetMetaData rsmds = (ResultSetMetaData) result.getMetaData();
                    int columnsNumbers = rsmds.getColumnCount();
                    // print the result
                    while (result.next()) {
                        for (int i = 1; i <= columnsNumbers; i++) {
                            brid = result.getString(i); // Stores the borrower id
                            System.out.println("For reference: Borrower Id= " + result.getString(i)); //Print one element of a row
                        }

                    }

                    //END of the IF
                } else if (uCBid == 0) {
                    // when the user knows the borrower id
                    System.out.println("Enter the Borrower_id");
                    brid = input.nextLine();
                    String ckBid = "SELECT * FROM borrowers WHERE borrower_id=\"" + brid + "\" ";
                    ResultSet result = stat.executeQuery(ckBid);
                    if (!(result.next())) {
                        System.out.println("Invalid Borrower id.");
                    }
                    //end of else if
                }
                // setting the borrow date and expected return dates
                String brdate = LocalDate.now().toString();
                System.out.println("Borrow Date: " + brdate);
                String erdate = LocalDate.now().plusMonths(1).toString();
                System.out.println("Expected return date" + erdate);

                int retuned = 0; // if returned the value would be 1, otherwise 0
                String areturndate = "null";

                System.out.println("\nEnter Librarian phone number");
                String lbph_num = input.next();
                String ckBid = "SELECT librarian_id FROM librarian WHERE phone_num=\"" + lbph_num + "\" ";
                // send to the database
                ResultSet result = stat.executeQuery(ckBid);
                ResultSetMetaData rsmds = (ResultSetMetaData) result.getMetaData();
                int columnsNumbers = rsmds.getColumnCount();
                // create variable to store librarian id
                String lbid = "";
                // print the result
                while (result.next()) {
                    for (int i = 1; i <= columnsNumbers; i++) {
                        lbid = result.getString(i); // Stores the borrower id
                        System.out.println("For reference: Librian Id= " + result.getString(i)); //Print one element of a row
                    }

                }
                String regformat = "INSERT INTO borrow_lst VALUES (\'" + id + "\',\'" + brid +
                        "\',\'" + brdate + "\',\'" + erdate + "\',\'" + retuned + "\'," + areturndate + ",\'"
                        + lbid + "\')";

                String bokformat = "UPDATE books SET avail=0, borrow_lst_id=\'" + id + "\' WHERE title=\'" + titile + "\'";


                // Update the database
                stat.executeUpdate(regformat);
                stat.executeUpdate(bokformat);
                System.out.println("Borrow registered under borrow list id of " + id + " for the book " + titile);
            }
            //END OF OPTION 6
        } else if (choice == 7) {
            // OPTION 7
            // Register return

            // get borrower id through asking their phone number
            // since no two people would have identical phone numbers
            System.out.println("Enter the phone number of the borrower");
            String brph_num = input.next();
            String ckBid = "SELECT borrower_id FROM borrowers WHERE phone_num=\"" + brph_num + "\" ";
            // send to the database
            ResultSet result = stat.executeQuery(ckBid);
            ResultSetMetaData rsmds = (ResultSetMetaData) result.getMetaData();
            int columnsNumbers = rsmds.getColumnCount();
            // create variable to store borrower id
            String brid = "";
            // print the result
            while (result.next()) {
                for (int i = 1; i <= columnsNumbers; i++) {
                    brid = result.getString(i); // Stores the borrower id
                    System.out.println("For reference: Borrower Id= " + result.getString(i)); //Print one element of a row
                }
            }

            // get the title of the returned book
            System.out.println("Enter the title of the book");
            String title = input.nextLine();
            title += input.nextLine();
            System.out.println("");

            // get the borrow list id from books table using title of the returned book
            String bokformat = "SELECT borrow_lst_id FROM books WHERE title=\'" + title + "\'";
            ResultSet res = stat.executeQuery(bokformat);
            ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            // create variable to store borrow lst id
            String brlstid = "";
            // print the result
            while (res.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    brlstid = res.getString(i); // Stores the borrow lst id
                    System.out.println("For reference: Borrow lst Id= " + res.getString(i)); //Print one element of a row
                }
            }

            String areturndate = LocalDate.now().toString(); // getting the current date for actual return date

            //update borrow list table
            String returnSt = "UPDATE borrow_lst SET returned=1, actual_return=\'" + areturndate + "\' WHERE borrow_id=\'" +
                    brid + "\' AND borrow_lst_id=\'" + brlstid + "\' AND returned=0";
            stat.executeUpdate(returnSt); // sending command to database

            //update the information in the book table
            String bookUpd = "UPDATE books SET avail=1, borrow_lst_id=null WHERE title=\'" + title + "\' AND " +
                    "borrow_lst_id=\'" + brlstid + "\'";
            stat.executeUpdate(bookUpd);
            System.out.println("Book Is returned ");

            //END OF OPTION 7
        }
    }
}