import java.util.Scanner;

public class BankAccount {
    double saldo = 0;
    boolean withdraw(double amount) {
        if (saldo < amount) {
            return false;
        } else {
            saldo -= amount;
            return true;
        }
    }

    double add(double amount) {
        saldo += amount;
        return saldo;
    }

    void interact() {
        boolean goOn = true;
        Scanner inter = new Scanner(System.in);
        while (goOn) {
            System.out.println("Current saldo: " + saldo);
            System.out.println("Choose action: add, withdraw, or end");
            switch (inter.next()) {
                case "add":
                    System.out.println("Please specify amount");
                    try {
                        double amount = inter.nextDouble();
                        System.out.println("New saldo: " + add(amount));
                    } catch (Exception e) {
                        inter.nextLine();
                        System.out.println("Error processing amount: " + e);
                    }
                    break;
                case "withdraw":
                    System.out.println("Please specify amount");
                    try {
                        double amount = inter.nextDouble();
                        if (withdraw(amount)) {
                            System.out.println("Withdrawal successful. New saldo: " + saldo);
                        } else {
                            System.out.println("Insufficient funds");
                        }
                    } catch (Exception e) {
                        inter.nextLine();
                        System.out.println("Error processing amount: " + e);
                    }
                    break;
                case "end":
                    goOn = false;
                    break;
                default:
                    System.out.println("Unknown action");
            }
        }
        System.out.println("Thank you for using our bank!");
    }
}
