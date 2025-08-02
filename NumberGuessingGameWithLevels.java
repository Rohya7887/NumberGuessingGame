import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGameWithLevels {
    static Scanner sc = new Scanner(System.in);
    static int highScore = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println("🎮 Welcome to the Number Guessing Game with Levels!");
        
        boolean playAgain = true;

        while (playAgain) {
            int level = chooseLevel();
            int maxNumber = getMaxNumberForLevel(level);
            int maxAttempts = getMaxAttemptsForLevel(level);
            
            int attemptsUsed = playGame(maxNumber, maxAttempts);

            if (attemptsUsed > 0 && attemptsUsed < highScore) {
                highScore = attemptsUsed;
                System.out.println("🏆 New High Score: " + highScore + " attempt(s)!");
            } else if (attemptsUsed > 0) {
                System.out.println("⭐ Your attempts: " + attemptsUsed);
                System.out.println("💯 Current High Score: " + highScore);
            }

            System.out.print("\n🔁 Do you want to play again? (yes/no): ");
            playAgain = sc.nextLine().trim().equalsIgnoreCase("yes");
        }

        System.out.println("\n👋 Thank you for playing!");
    }

    static int chooseLevel() {
        while (true) {
            System.out.println("\n📋 Choose Level:");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");
            System.out.print("Enter your choice (1-3): ");

            try {
                int level = Integer.parseInt(sc.nextLine());
                if (level >= 1 && level <= 3) return level;
                else System.out.println("❗ Invalid choice. Please select between 1 and 3.");
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Enter a number (1-3).");
            }
        }
    }

    static int getMaxNumberForLevel(int level) {
        switch (level) {
            case 1: return 50;
            case 2: return 100;
            case 3: return 200;
            default: return 100;
        }
    }

    static int getMaxAttemptsForLevel(int level) {
        switch (level) {
            case 1: return 7;
            case 2: return 5;
            case 3: return 5;
            default: return 5;
        }
    }

    static int playGame(int maxNumber, int maxAttempts) {
        Random rand = new Random();
        int target = rand.nextInt(maxNumber) + 1;
        int attempts = 0;

        System.out.println("\n🎲 Guess the number between 1 and " + maxNumber);
        System.out.println("🔢 You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            try {
                int guess = Integer.parseInt(sc.nextLine());
                attempts++;

                if (guess < 1 || guess > maxNumber) {
                    System.out.println("❗ Please enter a number between 1 and " + maxNumber);
                } else if (guess == target) {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                    return attempts;
                } else if (guess < target) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Enter a valid number.");
            }
        }

        System.out.println("💥 Game Over! The correct number was: " + target);
        return -1;
    }
}
