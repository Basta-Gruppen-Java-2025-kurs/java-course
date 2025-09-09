import java.util.Random;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
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
                    NumberGame.Start();
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

        choice.close();
    }
}