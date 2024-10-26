import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String url ="jdbc:mysql://localhost:3306/tran";
        String name ="root";
        String pass = "abhijeet@2002";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.getException();
        }
        try{
            Connection con = DriverManager.getConnection(url,name,pass);
            System.out.println("Connection is done");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}