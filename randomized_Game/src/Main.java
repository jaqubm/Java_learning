import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        int max = Integer.parseInt(args[0]);

        while(true)
        {
            int randNum = ThreadLocalRandom.current().nextInt(0, max + 1);

            Scanner scan= new Scanner(System.in);

            System.out.println("Random number you need to guess is:  " + randNum);

            int round = 0;

            while(true) {
                System.out.print("Insert your guess: ");
                int x = scan.nextInt();

                round++;

                if(x == randNum) {
                    break;
                }
                else if( x < randNum) {
                    System.out.println("The number you guessed is too small");
                }
                else {
                    System.out.println("The number you guessed is too big");
                }
            }

            System.out.println("Congratulations!! You won! The number you were trying to guess was " + randNum);
            System.out.println("You guessed it in: " + round + " rounds.");

            int cont;
            do {
                System.out.println("Do you want to continue playing? 1 - YES! 2 - NO!");
                cont = scan.nextInt();
            } while (cont != 1 && cont != 2);

            if(cont == 2) {
                break;
            }
        }

        System.out.println("Thank you for playing!");
    }
}