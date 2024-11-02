package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AccountManage {
    private final Connection con;
    private final Scanner sc;

    AccountManage(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void debit(long accNum) {

        sc.nextLine();
        System.out.println("Enter the Amount : ");
        double amt = sc.nextDouble();
        sc.nextLine();
        System.out.println("enter Security Pin");
        String pin = sc.nextLine();

        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
            ps.setLong(1, accNum);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double current_balance = rs.getDouble("balance");
                if (amt <= current_balance) {
                    String creditQ = "update accounts set balance = balance - ? where account_number=?";
                    PreparedStatement ps2 = con.prepareStatement(creditQ);
                    ps2.setDouble(1, amt);
                    ps2.setLong(2, accNum);
                    int row = ps2.executeUpdate();
                    if (row > 0) {
                        System.out.println("rs" + amt + "Debited");
                        con.commit();
                        con.setAutoCommit(true);
                        return;
                    } else {
                        System.out.println("transcation Failed!");
                        con.rollback();
                        con.setAutoCommit(false);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void credit(long accNum) {
        sc.nextLine();
        System.out.println("Enter the Amount : ");
        double amt = sc.nextDouble();
        sc.nextLine();
        System.out.println("enter Security Pin");
        String pin = sc.nextLine();
        if (accNum != 0) {


            try {
                con.setAutoCommit(false);
                PreparedStatement ps = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
                ps.setLong(1, accNum);
                ps.setString(2, pin);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    double current_balance = rs.getDouble("balance");

                    String creditQ = "update accounts set balance = balance + ? where account_number=?";
                    PreparedStatement ps2 = con.prepareStatement(creditQ);
                    ps2.setDouble(1, amt);
                    ps2.setLong(2, accNum);
                    int row = ps2.executeUpdate();
                    if (row > 0) {
                        System.out.println("rs" + amt + "Credited");
                        con.commit();
                        con.setAutoCommit(true);
                        return;
                    } else {
                        System.out.println("transcation Failed!");
                        con.rollback();
                        con.setAutoCommit(false);
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void transfer(long accNum) {

    }

    public void check(long accNum) {
     sc.nextLine();
        System.out.println("Enter Security pin : ");
        String pin = sc.nextLine();

        try{PreparedStatement ps = con.prepareStatement("select * from accounts where account_number = ? and security_pin = ?");
        ps.setLong(1,accNum);
        ps.setString(2,pin);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("Name : "+ rs.getString("full_name"));
            System.out.println("Account Number : " + accNum);
            System.out.println("Current Balance : " + rs.getDouble("balance"));
            System.out.println("Email Id : " + rs.getString("email"));
            System.out.println("Security Pin : "+ rs.getString("security_pin"));
        return;
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
