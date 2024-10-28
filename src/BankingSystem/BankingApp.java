package BankingSystem;

public class BankingApp {
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/tran";
        String name ="root";
        String pass = "";
        String que = "insert into emp(name,job,sal) values(?,?,?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("The Drivers are loaded");
        }catch (ClassNotFoundException e){
            e.getException();
        }
    }
}
