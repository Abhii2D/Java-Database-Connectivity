package BankingSystem;

import java.sql.Connection;
import java.util.Scanner;

public class Account {
    private final Connection con;
    private final Scanner sc;
   public Account(Connection con , Scanner sc){
        this.con = con;
        this.sc = sc;
    }
}
