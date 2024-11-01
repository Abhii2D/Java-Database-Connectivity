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

 public void debit(long accNum){
     System.out.println("hello debit");

 }public void credit(long accNum){
        System.out.println("hello credit");

 }public void transfer(long accNum){
        System.out.println("hello transfer");

 }public void check(long accNum){
        System.out.println("hello check");

 }






























}
