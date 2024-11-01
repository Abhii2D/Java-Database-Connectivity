package BankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class BankingApp {
    private static final String url = "jdbc:mysql://localhost:3306/bankingsystem";
    private static final String name = "root";
    private static final String pass = "abhijeet@2002";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("The Drivers are loaded");
        } catch (ClassNotFoundException e) {
            e.getException();
        }
        try {
            Connection con = DriverManager.getConnection(url, name, pass);
            Scanner sc = new Scanner(System.in);
            User user = new User(con, sc);
            Account ac = new Account(con, sc);
            AccountManage am = new AccountManage(con, sc);

            String email;
            long accountNumber;
            while (true) {
                System.out.println("____ Welcome to Banking System ____");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("enter your choice : ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        user.register();
                        System.out.println("");
                        System.out.flush();
                        break;
                    case 2:
                        email = user.login();
                        if (email != null) {
                            System.out.println();
                            System.out.println("User LogIN !");
                            if (!ac.account_exit(email)) {
                                System.out.println();
                                System.out.println(" 1 Open New Bank account");
                                System.out.println(" 2 Exit ");
                                if (sc.nextInt() == 1) {
                                    accountNumber = ac.openaccount(email);
                                    System.out.println("Account Created Successfully .........");
                                    System.out.println("Your Account Number is : " + accountNumber);
                                }

                            }
                        }


                        accountNumber = ac.getAccount_num(email);
                        int choice1 = 0;
                        while (choice1 != 5){
                            System.out.println();
                            System.out.println("1 > Debit Money");
                            System.out.println("2 > Credit Money");
                            System.out.println("3 > Transfer Money");
                            System.out.println("4 > Check Balance");
                            System.out.println("5 > Log Out ");
                            System.out.println("enter your Choice : ");
                            choice1 = sc.nextInt();
                            switch (choice1){
                                case 1:
                                    am.debit(accountNumber);
                                    break;
                                case 2:
                                    am.credit(accountNumber);
                                    break;
                                case 3:
                                    am.transfer(accountNumber);
                                    break;
                                case 4:
                                    am.check(accountNumber);
                                    break;
                                case 5:
                                    return;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + choice1);
                            }

                        }
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
