import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

class reservation{
    public boolean credentials(){
        System.out.println("Enter username:");
        Scanner sc=new Scanner(System.in);
        String uname=sc.nextLine();
        System.out.println("Enter password:");
        String pass=sc.nextLine();
        if (verifylogin(uname,pass) == true){
            System.out.println("\nLogin Successful!\n");
            return true;
            
        }
        else{
            System.out.println("\nLogin Failed!!Please enter correct details\n");
            credentials();
            return true;
        }
        
    }
    public boolean verifylogin(String uname,String pass) {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM login WHERE uname=?");
            pst.setString(1, uname);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                if(rs.getString(2).equals(pass)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        return true;
    }

    public void insert(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Train Details:");
        System.out.println("----------------------");
        Random ran = new Random();
        int pnr = ran.nextInt(Integer.MAX_VALUE) + 1;
        System.out.println("Your PNR number:"+pnr);
        System.out.println("Enter train number:");
        int train_number=sc.nextInt();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM train_info WHERE train_number=?");
            pst.setInt(1, train_number);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
            {
                String train_name = rs.getString(2);
                System.out.println("Train name: "+ train_name);
            }
            else{
                System.out.println("Train number invalid\n ");
            }    
            
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        Scanner sc1=new Scanner(System.in);
        System.out.println("Enter class type:");
        String classtype = sc1.nextLine();
        System.out.println("Enter Date of Journey(YYYY-MM--DD):");
        String date = sc1.nextLine();
        System.out.println("Enter Starting Location :");
        String from = sc1.nextLine();
        System.out.println("Enter Destination:");
        String destination = sc1.nextLine();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
            PreparedStatement pst1 = con.prepareStatement("INSERT INTO cust_info (pnr, train_number, class_type, date_of_journey, starting_loc, destination) VALUES ( ?, ?, ?, ?, ?,?)");

            pst1.setInt(1, pnr); 
            pst1.setInt(2, train_number); 
            pst1.setString(3, classtype); 
            pst1.setString(4, date); 
            pst1.setString(5, from); 
            pst1.setString(6, destination); 
            int rs = pst1.executeUpdate();
            if(rs>0){
                System.out.println("\nRecord inserted Successfully!!!!\n");
            }
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
}

public void delete(){
    Scanner sc = new Scanner(System.in);
    System.out.println("\nYOU HAVE CHOSEN TO DELETE A RECORD");
    System.out.println("Enter PNR number:");
    int pnr = sc.nextInt();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
        PreparedStatement pst = con.prepareStatement("SELECT * FROM cust_info WHERE pnr=?");
        pst.setInt(1, pnr);
        ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                System.out.println("\nTrain Details:");
                System.out.println("Train number:"+rs.getInt(2));
                System.out.println("Class Type:"+rs.getString(3));
                System.out.println("Date of Journey:"+rs.getDate(4));
                System.out.println("Starting Location:"+rs.getString(5));
                System.out.println("Destination:"+rs.getString(6));
            }
            else{
                System.out.println("PNR invalid ");
            }   
        System.out.println("\nConfirm Delete?(1-Yes or 0-No)");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                 pst = con.prepareStatement("Delete FROM cust_info WHERE pnr=?");
                 pst.setInt(1, pnr);
                 int rs1 = pst.executeUpdate();
                 if (rs1 > 0) { 
                    System.out.println("\nRecord deleted successfully!\n");
                } else {
                    System.out.println("\nNo record found with the given PNR.\n");
                }
                break;
            case 2:
                    System.out.println("\nYou have chosen not to delete record\n");
                break;
            default:
                System.out.println("Invalid choice");
                break;
    } 
}
    catch (Exception e) {
        System.out.println("Exception"+e);
    }
    
}

}


public class mainclass{
    public static void main(String[] args){
        reservation obj = new reservation();
        boolean result = obj.credentials();
        mainclass sel = new mainclass();
        sel.select(result,obj);
    }
    public void select(boolean result,reservation obj){
        if (result == true){
            System.out.println("1 : Insert");
            System.out.println("2 : Delete");
            System.out.println("3 : Exit");
            System.out.println("Enter choice(1-3)");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    obj.insert();
                    select(true,obj);
                    break;
                case 2:
                    obj.delete();
                    select(true,obj);
                    break;
                case 3:
                    System.out.println("You have chosen to exit");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;

            }


            
        }
    }
}
