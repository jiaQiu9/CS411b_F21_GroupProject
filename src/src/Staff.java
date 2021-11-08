public class Staff extends Person{
    protected double salary;

    public Staff(String n, String pw, int pnum, double s  ){
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


        }
