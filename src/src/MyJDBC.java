import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb","root","09062000");

            Statement statement =connection.createStatement();

            ResultSet resultSet=statement.executeQuery("select * from books");

            while(resultSet.next()){
                System.out.println(resultSet);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
