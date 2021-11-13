public class Borrower extends Person{
    protected String id;
    public Borrower(String n, String pw, int pnum, String idt){
        super(n, pnum, pw);
        id=idt;
    }

    // record borrowed books
    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Borrower id: "+id+"\n");
    }

}
