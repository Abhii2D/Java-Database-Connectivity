package BankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class BankingApp {
    private static final String url ="jdbc:mysql://localhost:3306/bankingsystem";
    private static final String name ="root";
   private static final String pass = "abhijeet@2002";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("The Drivers are loaded");
        }catch (ClassNotFoundException e){
            e.getException();
        }
        try{
            Connection con = DriverManager.getConnection(url,name,pass);
            Scanner sc =new Scanner(System.in);
            User user = new User(con,sc);
          //  Account ac = new Account(con,sc);
         //   AccountManage am = new AccountManage(con,sc);

            String email;
            long accountNumber;
            while (true){
                System.out.println("____ Welcome to Banking System ____");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("enter your choice : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        user.register();
                        System.out.println("");
                        System.out.flush();
                        break;
                    case 2:
                        System.out.println("Login Page into into system");
                        break;
                    case 3:
                        System.out.println("Thankup for visiting application");
                        return;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
