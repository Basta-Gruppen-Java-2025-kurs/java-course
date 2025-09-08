import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        boolean goOn = true;
        BankAccount account = new BankAccount();
        while (goOn) {
            System.out.println("""
                    Please input task and parameters. The following are available:
                    - "age" <int> - return the age group of a person of a given age
                    - "points" <double> - return a letter rating for given points (0-100)
                    - "calc" <first> <op> <second> - return a result of an arithmetic operation op (+-*/) between 2 numbers
                    - "temp" ("F-C" or "C-F") <temperature> - convert given temperature from Fahrenheit to Celsius or vice versa and give recommendation
                    - "bank" - interact with your bank account
                    - "exit" or "quit" - end the interaction
                    """);
            switch (choice.next()) {
                case "age":
                    try {
                        int age = choice.nextInt();
                        System.out.println("Age category: " + ageCategory(age));
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error processing input age: " + e);
                    }
                    break;
                case "points":
                    try {
                        double points = choice.nextDouble();
                        System.out.println("Rating: " + pointsToLetter(points));
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error processing points: " + e);
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
                        System.out.println("Error calculating: " + e);
                    }
                    break;
                case "temp":
                    try {
                        String conversionVariant = choice.next();
                        double temp = choice.nextDouble();
                        TemperatureAdvice ta;
                        if(conversionVariant.equals("F-C")) {
                            ta = convertTemperature(temp, false);
                        } else if (conversionVariant.equals("C-F")) {
                            ta = convertTemperature(temp, true);
                        } else {
                            throw new RuntimeException("Wrong conversion variant.");
                        }
                        System.out.println("Celsius: " + ta.celsius + "°");
                        System.out.println("Fahrenheit: " + ta.fahrenheit + "°");
                        System.out.println("Recommendation: " + ta.recommendation + "°");
                    } catch (Exception e) {
                        choice.nextLine();
                        System.out.println("Error converting temperature: " + e);
                    }
                    break;
                case "bank":
                    account.interact();
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
     * @return the result of operation as <b>double</b>
     */
    static double calculate2(double first, char op, double second) {
        return switch (op) {
            case '+' -> first + second;
            case '-' -> first - second;
            case '*' -> first * second;
            case '/' -> first / second;
            default -> throw new RuntimeException("Unknown operation");
        };
    }

    /**
     * Takes in temperature and converts it to Celsius or to Fahrenheit and gives a recommendation
     * @param temperature temperature value in degrees
     * @param isCelsius if true, convert Celsius to Fahrenheit, otherwise convert Fahrenheit to Celsius
     * @return a value of class {@link TemperatureAdvice TemperatureAdvice}
     */
    static TemperatureAdvice convertTemperature (double temperature, boolean isCelsius) {
        TemperatureAdvice result = new TemperatureAdvice();
        if (isCelsius) {
            result.celsius = temperature;
            result.fahrenheit = (temperature * 1.8) + 32;
        } else {
            result.fahrenheit = temperature;
            result.celsius = (temperature - 32) * 0.5556;
        }
        if (result.celsius < 0) {
            result.recommendation = "Mycket kallt - ta på dig vinterkläder!";
        } else if (result.celsius <= 10) {
            result.recommendation = "Kallt - jacka behövs";
        } else if (result.celsius <= 20) {
            result.recommendation = "Svalt - lätt jacka";
        } else if (result.celsius <= 30) {
            result.recommendation = "Behagligt - t-shirt räcker";
        } else {
            result.recommendation =  "Varmt - shorts och linne!";
        }
        return result;
    }
}

/**
 * Contains 3 values:<br/>
 * <b>double</b> <tt>celsius</tt> &mdash; temperature in Celsius<br/>
 * <b>double</b> <tt>fahrenheit</tt> &mdash; temperature in Fahrenheit<br/>
 * <b>String</b> <tt>recommendation</tt> &mdash; a recommendation for clothes at this temperature
 */
class TemperatureAdvice {
    double celsius;
    double fahrenheit;
    String recommendation;
}
