package BankingSystem;

import java.sql.Connection;
import java.util.Scanner;

public class AccountManage {
    private final Connection con;
    private final Scanner sc;
AccountManage(Connection con, Scanner sc){
    this.con = con;
    this.sc = sc;
}
}
