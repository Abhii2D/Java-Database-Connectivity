package BankingSystem;

import java.sql.*;
import java.util.Scanner;

public class Account {
    private final Connection con;
    private final Scanner sc;
   public Account(Connection con , Scanner sc){
        this.con = con;
        this.sc = sc;
    }

public boolean account_exit(String email){
String EmailQ = "select * from accounts where email = ?";
try{
    PreparedStatement ps = con.prepareStatement(EmailQ);
    ps.setString(1,email);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        return true;
    }
} catch (Exception e) {
    throw new RuntimeException(e);
}
return false;
}


public long openaccount(String email){
String CreateAc = "insert into accounts(account_number,full_name,email,balance,security_pin) values(?,?,?,?,?)";
if(!account_exit(email)){
    sc.nextLine();
    System.out.println("Welcome Please fulfill the give Inforamtion!!!!!1");
    System.out.print("Enter Full_Name : ");
    String name = sc.nextLine();
    System.out.print("Enter Initial Account : ");
    double amt = sc.nextDouble();
    sc.nextLine();
    System.out.print("Enter The Security Pin : ");
    String pin = sc.nextLine();
    try{
        long accNum = generateAccNumber();
        PreparedStatement ps = con.prepareStatement(CreateAc);
        ps.setLong(1,accNum);
        ps.setString(2,name);
        ps.setString(3,email);
        ps.setDouble(4,amt);
        ps.setString(5,pin);
       int row = ps.executeUpdate();
       if(row > 0){
           return accNum;
       }else {
           throw new RuntimeException("Account Creation Failed!");
       }
    }catch (Exception e){
        e.printStackTrace();
    }
   }else{
    System.out.println("Account Already Existing");
   }
    throw new RuntimeException("Account is already existing");

}


private long generateAccNumber(){
       String Gen = "select account_number from accounts order by account_number desc limit 1";
       try{
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(Gen);
           if(rs.next()){
               long last_ac_num = rs.getLong("account_number");
                return last_ac_num + 1;
           }else{
               return 11122233;
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
}

   public long getAccount_num(String email) {
        String query = "SELECT account_number FROM accounts WHERE email = ?";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email); // Set the email parameter
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getLong("account_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }

        throw new RuntimeException("Account number does not exist for the given email.");
    }




}
