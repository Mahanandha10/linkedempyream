package dow;
import java.util.Random;
import java.util.Scanner;

public class guess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 7;
        int rounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("You have " + maxAttempts + " attempts per round.");
        System.out.println("There are " + rounds + " rounds.");

        for (int round = 1; round <= rounds; round++) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            int score = maxAttempts;

            System.out.println("\nRound " + round + ":");
            System.out.println("Guess the number between " + minRange + " and " + maxRange);

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number.");
                    score = maxAttempts - attempts + 1;
                    totalScore += score;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }
            }

            System.out.println("Round " + round + " Score: " + score);
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}