import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String url ="jdbc:mysql://localhost:3306/tran";
        String name ="root";
        String pass = "";
        String que = "insert into emp(name,job,sal) values(?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.getException();
        }




    }
}
