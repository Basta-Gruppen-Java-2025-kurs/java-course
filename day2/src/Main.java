import java.util.Random;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void NumberGame()
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);


        //int[] secretNumbers = {1,3,3,3,3};
        int[] secretNumbers = new int[5];
        int[] guessedNumbers = new int[5];

        for (int index = 0; index < secretNumbers.length; index++)
        {
            secretNumbers[index] = random.nextInt(1,20);
        }

        int guesses = 0;
        int maxGuesses = 15;

        while(guesses < maxGuesses)
        {
            for (int i = 0; i < secretNumbers.length; i++)
            {
                System.out.println("Guess the " + (i+1) + " number");

                try
                {
                    guessedNumbers[i] =  Integer.parseInt(scanner.nextLine());
                }
                catch(Exception e)
                {
                    i--;
                    continue;
                }
            }

            int correctGuesses = 0;
            int wrongPlaceGuesses = 0;

            boolean[] matchedSecrets = new boolean[secretNumbers.length];
            boolean[] matchedGuesses = new boolean[guessedNumbers.length];

            for (int i = 0; i < guessedNumbers.length; i++)
            {
                if (guessedNumbers[i] == secretNumbers[i])
                {
                    correctGuesses++;
                    matchedSecrets[i] = true;
                    matchedGuesses[i] = true;
                }
            }

            for (int i = 0; i < guessedNumbers.length; i++)
            {
                if (matchedGuesses[i]) continue;

                for (int j = 0; j < secretNumbers.length; j++)
                {
                    if(matchedSecrets[j]) continue;
                    if (guessedNumbers[i] == secretNumbers[j])
                        {
                            wrongPlaceGuesses++;
                            matchedSecrets[j] = true;
                            matchedGuesses[i] = true;
                            break;
                        }
                }
            }

            guesses++;

            if (correctGuesses == secretNumbers.length)
            {
                System.out.println("You got all guesses correct! Well done!");
                break;
            }
            else
            {
                System.out.println("You got " + correctGuesses + " correct");
                System.out.println("and you got " + wrongPlaceGuesses + " correct but in the wrong position");
                System.out.println("try again, you have " + (maxGuesses - guesses) + " guesses left");
            }

            scanner.close();
        }
    }

    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        boolean goOn = true;
        while (goOn) {
            System.out.println("""
                    Please input task and parameters. The following are available:
                    - "temp" - Temperaturanalys för en vecka
                    - "student" - Studentbetyg för en klass
                    - "utgifter" - Veckans utgifter
                    - "priser" - Handelslista med priser
                    - "tallek" - Enkel tallek
                    - "exit" or "quit" - end the interaction
                    """);
            switch (choice.next()) {
                case "temp":
                    TemperaturAnalys.run();
                    break;
                case "student":
                    System.out.println("Not implemented yet");
                    break;
                case "utgifter":
                    System.out.println("Not implemented yet");
                    break;
                case "priser":
                    System.out.println("Not implemented yet");
                    break;
                case "tallek":
                    NumberGame();
                    break;
                case "exit", "quit":
                    goOn = false;
                    System.out.println("Good bye.");
                    break;
                default:
                    System.out.println("Unexpected request");
                    break;
            }
        }
    }
}