import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String url ="jdbc:mysql://localhost:3306/tran";
        String name ="root";
        String pass = "abhijeet@2002";
        String que = "";
          try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.getException();
        }



    }
}