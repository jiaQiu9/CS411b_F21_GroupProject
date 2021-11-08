// Authors: CS 411 b F21 Group 2
public abstract class Person{
    //person class for the general attributes of the librarian and the borrowers

    protected String password; // the password for each user
    protected String name; // user's name
    protected int phoneNum; // user's phone number

    protected String currentIdN; // id of the person in string forma
                                // format: combination of name and phone number
                                // no space between name and phone number

    // setting inital attributes for the person object
    public Person( String nam, int pnum,String pw)
    {
        currentIdN= nam+Integer.toString(pnum);
        password=pw;
        name=nam;
        phoneNum=pnum;
    }

    //printing the information of the person
    public void printInfo(){
        System.out.println("Information List\n");
        System.out.println("Identification Number: "+currentIdN);
        System.out.println("Name of the person: "+ name);
        System.out.println("Phone number: "+phoneNum+"\n");
    }

    /*setter functions/methods */

    // setting the name of the person
    public void setName(String nam){
        name=nam;
    }
    // setting the phone number of the person
    public void setPhone(int pnum){
        phoneNum=pnum;
    }

    // setting password
    public void setPassword(String pw){
        password=pw;
    }

    /* getter functions/methods */

    // get name function/methods
    public String getName(){
        return name;
    }

    // get phone number function/methods
    public int getPhoneNum(){
        return phoneNum;
    }

    // get password function/methods
    public String getPassword(){
        return password;
    }

    // get id number function/method
    public String getCurrentIdN(){
        return currentIdN;
    }


}