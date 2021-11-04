// Authors: CS 411 b F21 Group 2
public abstract class Person{
    //person class for the general attributes of the librarian and the borrowers
    protected int id; // the id number for each user
    protected String password; // the password for each user
    protected String name; // user's name
    protected int phoneNum; // user's phone number

    static String currentIdN; // id counter for creating unique id

    // setting inital attributes for the person object
    public Person(int dd, String nam, int pnum)
    {
        currentIdN= nam+Integer.toString(pnum)
        password=Integer.toString(id);
        name=n;
        phoneNum=p;
    }
    // setting the name of the person
    public void setName(String name){
        this.name=name;
    }

}