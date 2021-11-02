public abstract class Person{
    //person class for the general attributes of the librarian and the borrowers
    protected int id; // the id number for each user
    protected String password; // the password for each user
    protected String name; // user's name
    protected int phoneNum; // user's phone number

    static String currentIdN; // id counter for creating unique id

    public Person(int dd, String nam, int pnum)
    {
        currentIdN= nam+Integer.toString(pnum)
        password=Integer.toString(id);
        name=n;
        phoneNum=p;
    }

}