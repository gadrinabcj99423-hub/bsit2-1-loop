import java.util.Scanner;

public class Main {

    static double balance = 1000.00;

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

           String greeting = """
                   ========================
                   WELCOME TO THE MINI-ATM
                   =========================
                   \n""";
           System.out.print(greeting);

            boolean running = true;

            while (running) {

                printMenu();

                String choice = input.nextLine().trim();

                switch (choice) {

                    case "1":
                        deposit(input);
                        break;

                    case "2":
                        withdraw(input);
                        break;

                    case "3":
                        checkBalance();
                        break;

                    case "4":
                        running = false;
                        System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                        break;

                    default:
                        System.out.println("\n[!] Please choose a number from 1 to 4.\n");
                }
            }
        }
    }

    static void printMenu() {

        String Menu = """
                ===== Current Options =====
                [1] Deposit
                [2] Withdraw 
                [3] Check Balance
                [4] Exit 
                """;
        System.out.print(Menu);
        System.out.print("Please enter your Menu: ");
    }

    static void deposit(Scanner input) {

        System.out.print("Enter amount to deposit: ");
        String line = input.nextLine().trim();

        try {

            double amount = Double.parseDouble(line);

            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than zero.");
            }

            balance += amount;

            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n",
                    amount, balance);

        } catch (NumberFormatException e) {

            System.out.println("[!] Please enter a valid number.");

        } catch (InvalidAmountException e) {

            System.out.println("[!] " + e.getMessage());

        } finally {

            System.out.println("-- transaction finished --");
        }
    }

    static void withdraw(Scanner input) {

        System.out.print("Enter amount to withdraw: ");
        String line = input.nextLine().trim();

        try {

            double amount = Double.parseDouble(line);

            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than zero.");
            }

            if (amount > balance) {

                double shortfall = amount - balance;

                throw new InsufficientFundsException(
                        "Insufficient funds. You are short by PHP "
                                + String.format("%.2f", shortfall),
                        shortfall);
            }

            balance -= amount;

            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n",
                    amount, balance);

        } catch (NumberFormatException e) {

            System.out.println("[!] Please enter a valid number.");

        } catch (InvalidAmountException | InsufficientFundsException e) {

            System.out.println("[!] " + e.getMessage());

        } finally {

            System.out.println("-- transaction finished --");
        }
    }

    static void checkBalance() {

        System.out.printf("%nCurrent Balance: PHP %.2f%n", balance);
    }
}



class InsufficientFundsException extends Exception {

    private double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}



class InvalidAmountException extends Exception {

    public InvalidAmountException(String message) {
        super(message);
    }
}