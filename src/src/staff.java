public class staff extends Person{
    protected double salary;

    public staff (String id, String pw, int pnum, double s  ){
        super(id, pnum, pw);
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
