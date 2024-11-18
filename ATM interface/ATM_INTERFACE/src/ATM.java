import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class Functionalities{
    private String username;
    private String pass;
    private int noOfTrans = 0;
    private String trans_hist = "";
    private String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private String user = "root";
    private String password = "root";
    
    Scanner sc = new Scanner(System.in);

    public boolean Credentials(){
        System.out.println("Welcome to ATM interface");
        System.out.println("--------------------------");
        System.out.println("Enter username:");
        username=sc.nextLine();
        System.out.println("Enter 4-digit pin:");
        pass=sc.nextLine();
        if (verifyLogin(username,pass) == true){
            return true;
        }
        else{

            Credentials();
            return true;
        }
        
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyLogin(String username,String pass) {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM atm_details WHERE username=?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                if(rs.getString(2).equals(pass)){
                    System.out.println("Login Successfull\n");
                    System.out.println("Welcome " + rs.getString(3)+"\n");
                    return true;
                }
                else{
                    System.out.println("Login Failed!!Please enter correct details");
                    return false;
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        return false;
    }

    public void Transaction_Hist(){
        if (noOfTrans == 0){
            System.out.println("No Transactions occurred");
        }
        else{
            System.out.println(trans_hist +"\n");
        }

    }

    public void Withdraw(String username){
        System.out.println("Enter amount to withdraw:");
        float withdraw = sc.nextFloat();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst1 = con.prepareStatement("SELECT * FROM atm_details WHERE username=?");
            pst1.setString(1, username);
            ResultSet rs1 = pst1.executeQuery();
            float balance =0;
            if (rs1.next()){
                balance = rs1.getFloat(4);
            }

            if (balance > withdraw){
                PreparedStatement pst2 = con.prepareStatement("UPDATE atm_details SET balance=? WHERE username=?");
                pst2.setFloat(1, balance - withdraw);
                pst2.setString(2, username);
                int rs2 = pst2.executeUpdate();
                if (rs2 > 0)
                {
                    System.out.println("Amount withdrawn successfully. Your new balance is: "+ (balance - withdraw));
                    noOfTrans++;
                    String msg = String.valueOf(withdraw) + " Withdrawn\n";
                    trans_hist = trans_hist.concat(msg);
                }
                else{
                    System.out.println("Withdrawal failed");
                }
            }
            else{
                System.out.println("Withdrawal amount exceeds account balance!!!!");
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        
}

    public void Deposit(String username){
        System.out.println("Enter amount to deposit:");
        float amount = sc.nextFloat();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst1 = con.prepareStatement("SELECT * FROM atm_details WHERE username=?");
            pst1.setString(1, username);
            ResultSet rs1 = pst1.executeQuery();
            float balance =0;
            if (rs1.next()){
                balance = rs1.getFloat(4);
            }
            
            PreparedStatement pst2 = con.prepareStatement("UPDATE atm_details SET balance=? WHERE username=?");
            pst2.setFloat(1, balance + amount);
            pst2.setString(2, username);
            int rs2 = pst2.executeUpdate();
            if (rs2 > 0)
            {
                System.out.println("Amount deposited successfully. Your new balance is: "+ (balance + amount));
                noOfTrans++;
                String msg = String.valueOf(amount) + " Deposited\n";
                trans_hist = trans_hist.concat(msg);
            }
            else{
                System.out.println("Amount deposit failed");
            }
    
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
    }

    public void transfer(String username){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Account username you want to transfer money to:");
        String trans_user = sc.nextLine();
        System.out.println("Enter the amount you want to transfer:");
        float trans_amount = sc.nextFloat();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst1 = con.prepareStatement("SELECT * FROM atm_details WHERE username=?");
            pst1.setString(1, username);
            ResultSet rs1 = pst1.executeQuery();
            float balance =0;
            if (rs1.next()){
                balance = rs1.getFloat(4);
            }
            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM atm_details WHERE username=?");
            pst2.setString(1, trans_user);
            ResultSet rs2 = pst2.executeQuery();
            float trans_balance =0;
            if (rs2.next()){
                trans_balance = rs2.getFloat(4);
            }

            PreparedStatement pst3 = con.prepareStatement("UPDATE atm_details SET balance=? WHERE username=?");
            pst3.setFloat(1, trans_balance + trans_amount);
            pst3.setString(2, trans_user);
            int rs3 = pst3.executeUpdate();
            if (rs3 > 0)
            {
                System.out.println("Amount transferred successfully.");
            }
            else{
                System.out.println("Amount transfer failed");
            }
            

            PreparedStatement pst4 = con.prepareStatement("UPDATE atm_details SET balance=? WHERE username=?");
            pst4.setFloat(1, balance - trans_amount);
            pst4.setString(2, username);
            int rs4 = pst4.executeUpdate();
            if (rs4 > 0)
            {
                System.out.println("Amount to be transferred deducted from your account successfully. Your new balance is: "+ (balance - trans_amount));
                noOfTrans++;
                String msg = String.valueOf(trans_amount) + " Transferred\n";
                trans_hist = trans_hist.concat(msg);
            }
            else{
                System.out.println("Amount transfer failed");
            }


    }
    catch(Exception e){
        System.out.println("Exception"+e);
    }
}
}

public class ATM {
    public static void main(String[] args) throws Exception {
        Functionalities obj = new Functionalities();
        boolean verify = obj.Credentials();
        ATM atm = new ATM();
        atm.start(verify, obj);

    }
    public void start(boolean verify,Functionalities obj){
        if(verify == true){
            Scanner sc = new Scanner(System.in);
            String username = obj.getUsername();
            System.out.println("1 - Transaction History");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Deposit");
            System.out.println("4 - Transfer");
            System.out.println("5 - Exit");
            System.out.println("Enter choice:");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    obj.Transaction_Hist();
                    start(verify, obj);
                    break;
                case 2:
                    obj.Withdraw(username);
                    start(verify, obj);
                    break;
                case 3:
                    obj.Deposit(username);
                    start(verify, obj);
                    break;
                case 4:
                    obj.transfer(username);
                    start(verify, obj);
                    break;
                case 5:
                    break;
                default:
                        System.out.println("Invalid choice");
            }
            


        }
    }
}
