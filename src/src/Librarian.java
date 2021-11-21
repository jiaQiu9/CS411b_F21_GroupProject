public class Librarian extends Person{
    protected double salary;

    public Librarian(String n, String pw, int pnum, double s  ){
        super(n, pnum, pw);
        salary=s;
        }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Salary: "+salary+"\n");
        }

    public double getSalary(){

        return salary;
        }
    public void setSalary(double s){
        salary=s;
    }
        }
