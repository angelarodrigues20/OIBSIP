import java.util.Random;
import java.util.Scanner;
public class number_guessing {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int num = rand.nextInt(100) + 1;

        System.out.println("Welcome To Number Guessing Game");
        System.out.println("A random number is generated!!!!\nThis number is within the range of 1-100");
        System.out.println("Number of Attempts:10");
        System.out.println("You have to guess this number.\n Let's Start ");
        int score = 100;
        int attempts = 10;
        int att = attempts;
        for(int i=0 ; i<attempts ; i++)
        {
            System.out.println("Enter your guess(1-100)");
            int guess = sc.nextInt();
            if(num != guess){
                if (guess > num){
                    score = score - 10;
                    System.out.println("Entered number is higher than generated number");
                    att = att - 1;
                    System.out.println("Attempts left:" + att);
                }
                else{
                    score = score - 10;
                    System.out.println("\nEntered number is lower than generated number");
                    att = att - 1;
                    System.out.println("Attempts left:" + att);
                }
            }
            else{
                System.out.println("Correct match!!");
                System.out.println("Your score:"+score);
                break;
            }
        }
}
}
