import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Timer;  
import java.util.TimerTask;  

class Functionalities{
    private String Stud_id;
    private String pass;
    private final int time_limit = 1 * 60 * 1000;
    private boolean timeUp = false;
    private String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private String user = "root";
    private String password = "root";
    
    Scanner sc = new Scanner(System.in);

    public boolean Credentials(){
        System.out.println("Welcome to Online Examination");
        System.out.println("--------------------------");
        System.out.println("Enter your Student ID:");
        Stud_id = sc.nextLine();
        System.out.println("Enter password:");
        pass = sc.nextLine();
        if (verifyLogin(Stud_id,pass) == true){
            return true;
        }
        else{
            Credentials();
            return true;
        }
        
    }

    public String getStudId() {
        return Stud_id;
    }

    public boolean verifyLogin(String Stud_id,String pass) {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM student_details WHERE Stud_ID=?");
            pst.setString(1, Stud_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                if(rs.getString(3).equals(pass)){
                    System.out.println("Login Successfull\n");
                    System.out.println("Welcome " + rs.getString(2)+"\n");
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
    public void edit_Profile(String Stud_Id){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        String stud_name = sc.nextLine();
        System.out.println("Enter new password:");
        String updated_pass = sc.nextLine();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement("UPDATE student_details SET Stud_Name=?,password=? WHERE Stud_ID=?");
            pst.setString(1, stud_name);
            pst.setString(2, updated_pass);
            pst.setString(3, Stud_id);
            int rs = pst.executeUpdate();
            if(rs > 0){
                System.out.println("Details updated successfully!!");
            }
            else{
                System.out.println("Updation failed!!");
            }
            
        }
        catch(Exception e){
            System.out.println("Exception"+e);
        }
        
    }
    public int examination(String Stud_Id){
        
        int percentage = 0;
        String[] questions ={
            " Q1. _____ is used to find and fix bugs in the Java programs.\n1. JVM\n 2. JRE\n 3. JDK\n 4. JDB",
            "Q2. In which memory a String is stored, when we create a string using new operator?\n1.Stack\n2.String memory\n3.Heap memory\n4.Random storage space",
            "Q3. Which keyword is used for accessing the features of a package?\n1. package\n2. import\n3. extends\n4. export",
            "Q4.How many threads can be executed at a time?\n1. Only one thread\n2. Multiple threads\n3. Only main (main() method) thread\n4. Two threads",
            "Q5. Which of the following modifiers can be used for a variable so that it can be accessed by any thread or a part of a program??\n1. global\n2. transient\n3. volatile\n4. default"
        };

        int [] answers ={4,3,2,2,3};

        System.out.println("Online Examination ");
        System.out.println("Student ID : "+ Stud_Id);
        System.out.println("You have 20 minutes to answer 5 MCQ based questions");
        System.out.println("Your answer should include only the number of option(i.e 1,2,3 or 4)");
        System.out.println("All the best!!");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true; 
                System.out.println("\nTime is up! Exam will auto-submit.");
                timer.cancel(); 
            }
        },  time_limit);

        for(int i= 0;i <= 4; i++){
            if (timeUp) {
               break;
            }
            System.out.println("\n");
            System.out.println(questions[i]);
            System.out.println("Answer:");
            if (!timeUp) {
                int ans = sc.nextInt();
                if (ans == answers[i]){
                    percentage = percentage + 20;
                }
            }

        }
        timer.cancel();
        return percentage;
    }

}

public class online_exam {
    public static void main(String[] args) throws Exception {
        Functionalities obj = new Functionalities();
        boolean result = obj.Credentials();
        online_exam oe = new online_exam();
        oe.start(obj,result);
    }
    public void start(Functionalities obj,boolean result){
        Scanner sc = new Scanner(System.in);
        if (result == true){
            String Stud_Id = obj.getStudId();
            System.out.println("1. Edit Profile");
            System.out.println("2. Start Exam");
            System.out.println("3. Log out\n");
            System.out.println("Enter choice(1,2 or 3):");
            int choice = sc.nextInt();
            if (choice>3){
                System.out.println("Please enter a valid choice!!");
                start(obj,result);
            }
            else{
                switch(choice){
                    case 1:
                        obj.edit_Profile(Stud_Id);
                        start(obj,result);
                        break;
                
                    case 2:
                        
                        int per = obj.examination(Stud_Id);
                        if(per > 40){
                            System.out.println("Percentage: "+ per +"%");
                            System.out.println("Result : Pass");
                        }
                        
                        else{
                            System.out.println("Percentage: "+ per +"%");
                            System.out.println("Result : Failed");
                        }

                        int select = 0;
                        while (select !=2){
                            System.out.println("1. Edit Profile");
                            System.out.println("2. Log out");
                            System.out.println("Enter choice(1 or 2):");
                            select = sc.nextInt();
                            if (select>2){
                                System.out.println("Enter either 1 or 2");
                                select = sc.nextInt();
                            }
                            if (select == 1){
                                obj.edit_Profile(Stud_Id);
                            }
                        }

                        if (select == 2){
                            break;
                        }

                        break;

                    case 3:
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                
                
                }
            }
        }
    }
}
