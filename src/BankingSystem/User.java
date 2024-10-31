package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private final Connection con;
    private final Scanner sc;

public User(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

// Creating the user Register Method
public void register(){
     sc.nextLine();
        System.out.print("Full Name : ");
        String fname = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("password : ");
        String password = sc.nextLine();

        // We check the above data user is register in system or not

        if(user_exit(email)){
            System.out.println("user Already Exists for this email Address !!");
            return;// this will stop the conditon  manun start once again
        }
        String Reg_Query = "insert into user(full_name,email,password)values(?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(Reg_Query);
            ps.setString(1,fname);
            ps.setString(2,email);
            ps.setString(3,password);
            int row = ps.executeUpdate();
            if(row > 0){
                System.out.println("Registration Successfull!!");
            }else{
                System.out.println("Registration Failed!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

public boolean user_exit(String email){
String CheckQuery = "select * from user where email = ? ";
try{

    try (PreparedStatement ps = con.prepareStatement(CheckQuery)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
    if(rs.next()){
        return true;
    }
    }
} catch (SQLException e) {
    e.printStackTrace();

}
return false;
}

public String login(){
sc.nextLine();
    System.out.println("Enter the Email ID : ");
    String Email = sc.nextLine();
    System.out.println("Enter the Password : ");
    String password = sc.nextLine();
    String Log = "select * from user where email = ? and password = ?";

    try {
        PreparedStatement ps = con.prepareStatement(Log);
        ps.setString(1,Email);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return Email;
        }else{
            return null;
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}





}
