import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        boolean goOn = true;
        while (goOn) {
            System.out.println("""
                    Please input task and parameters. The following are available:
                    - "age" <int> - return the age group of a person of a given age
                    - "points" <double> - return a letter rating for given points (0-100)
                    - "exit" or "quit" - end the interaction
                    """);
            switch (choice.next()) {
                case "age":
                    try {
                        int age = choice.nextInt();
                        System.out.println("Age category: " + ageCategory(age));
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error processing input age: " + e.getMessage());;
                    }
                    break;
                case "points":
                    try {
                        double points = choice.nextDouble();
                        System.out.println("Rating: " + pointsToLetter(points));
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error processing points: " + e.getMessage());
                    }
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

    /**
     * Returns a name of age category given an age as input
     * @param age person's age as <b>int</b>
     * @return person's age category as <b>String</b>
     */
    static String ageCategory(int age) {
        if (age < 0) {
            throw new RuntimeException("Negative age");
        }
        return  age < 13 ? "Barn" :
                age <= 19 ? "Tonåring" :
                age <= 64 ? "Vuxen" :
                "Senior";
    }

    static char pointsToLetter(double points) {
        if (points > 100) {
            throw new RuntimeException("Points over 100");
        } else if (points >= 90) {
            return 'A';
        } else if (points >= 80) {
            return 'B';
        } else if (points >= 70) {
            return 'C';
        } else if (points >= 60) {
            return 'D';
        } else {
            return 'F';
        }

    }

}