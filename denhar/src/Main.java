import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        boolean goOn = true;
        while (goOn) {
            System.out.println("""
                    Please input task and parameters. The following are available:
                    - "age" <int> - return the age group of a person of a given age
                    - "points" <double> - return a letter rating for given points (0-100)
                    - "calc" <first> <op> <second> - return a result of an arithmetic operation op (+-*/) between 2 numbers
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
                case "calc":
                    Pattern ops = Pattern.compile("[/*+-]", Pattern.CASE_INSENSITIVE);
                    try {
                        double first = choice.nextDouble();
                        char op = choice.next(ops).charAt(0);
                        double second = choice.nextDouble();
                        System.out.println("Result: " + calculate2(first, op, second));
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error calculating: " + e.getMessage());
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
     * Returns a name of age category given an age as input (Uppgift 1)
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

    /**
     * Returns a rating letter for a given number of points (0-100)
     * @param points number of points as <b>double</b>. If the points are above 100, an exception is thrown
     * @return rating as <b>char</b>
     */
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

    /**
     * Returns a result of an arithmetic operation between 2 numbers. Throws an exception if operation is unknown
     * @param first first number as <b>double</b>
     * @param op operation (+,-,*,/) as <b>char</b>
     * @param second second number as <b>double</b>
     * @return
     */
    static double calculate2(double first, char op, double second) {
        switch(op) {
            case '+' :
                return first + second;
            case '-' :
                return first - second;
            case '*' :
                return first * second;
            case '/' :
                return first / second;
            default:
                throw new RuntimeException("Unknown operation");
        }
    }

}