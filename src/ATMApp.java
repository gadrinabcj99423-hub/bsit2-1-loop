import java.util.Scanner;

abstract class Account {

    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double openingBalance) {

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("The Account Number is required");
        }

        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name is required.");
        }

        if (openingBalance < 0) {
            throw new IllegalArgumentException(
                    "Opening balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "The amount must be greater than 0."
            );
        }

        balance += amount;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    amount - balance
            );
        }

        balance -= amount;
    }

    public abstract String getAccountType();

    protected void applyWithdrawal(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return getAccountType() + " "
                + accountNumber
                + " (" + ownerName + ")";
    }
}

class SavingsAccount extends Account {

    public static final double BalancePerson = 500.0;

    private double interestRate;

    public SavingsAccount(
            String accountNumber,
            String ownerName,
            double openingBalance,
            double interestRate) {

        super(accountNumber, ownerName, openingBalance);

        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }

        double remaining = getBalance() - amount;

        if (remaining < BalancePerson) {
            throw new InsufficientFundsException(
                    BalancePerson - remaining
            );
        }

        super.withdraw(amount);
    }

    public double monthlyInterest() {
        return getBalance() * interestRate / 12;
    }
}

class CheckingAccount extends Account {

    private double overdraftLimit;

    public CheckingAccount(
            String accountNumber,
            String ownerName,
            double openingBalance,
            double overdraftLimit) {

        super(accountNumber, ownerName, openingBalance);

        if (overdraftLimit < 0) {
            throw new IllegalArgumentException(
                    "Overdraft limit cannot be negative."
            );
        }

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0."
            );
        }

        double remaining = getBalance() - amount;

        if (remaining < -overdraftLimit) {

            double shortfall =
                    (-overdraftLimit) - remaining;

            throw new InsufficientFundsException(shortfall);
        }

        applyWithdrawal(amount);
    }
}

class InsufficientFundsException extends Exception {

    private final double shortfall;

    public InsufficientFundsException(double shortfall) {

        super("Insufficient funds. You are short by PHP "
                + String.format("%.2f", shortfall));

        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

class ATMService {

    public void deposit(Account account, double amount) {

        account.deposit(amount);

        System.out.printf(
                "Deposited PHP %.2f%n",
                amount
        );
    }

    public void deposit(
            Account account,
            double amount,
            String note) {

        account.deposit(amount);

        System.out.printf(
                "Deposited PHP %.2f%n",
                amount
        );

        System.out.println("Note: " + note);
    }

    public double depositAll(
            Account account,
            double... amounts) {

        double total = 0;

        for (double amount : amounts) {
            account.deposit(amount);
            total += amount;
        }

        return total;
    }

    public void tryToReplace(Account account) {

        account = new SavingsAccount(
                "XX-000",
                "Ghost Account",
                0,
                0
        );

        System.out.println(
                "Inside the method: " + account
        );
    }

    public void addBonus(
            Account account,
            double bonus) {

        account.deposit(bonus);
    }

    public void transfer(
            Account from,
            Account to,
            double amount)
            throws InsufficientFundsException {

        from.withdraw(amount);
        to.deposit(amount);
    }
}

public class ATMApp {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            ATMService atm = new ATMService();

            Account account =
                    new SavingsAccount(
                            "SA-1001",
                            "Juan Dela Cruz",
                            5000.0,
                            0.02
                    );

            boolean running = true;

            while (running) {

                String menu = """
                        [1] Check Balance
                        [2] Deposit
                        [3] Withdraw
                        [4] Deposit Multiple Amounts
                        [5] Pass-by-Value Demo
                        [0] Exit
                        """;

                System.out.println(menu);

                printHeader(account);

                System.out.print("Please choose your choice: ");

                try {

                    int choice =
                            Integer.parseInt(
                                    input.nextLine()
                            );

                    switch (choice) {

                        case 1:

                            System.out.printf(
                                    "Balance: PHP %.2f%n",
                                    account.getBalance()
                            );

                            break;

                        case 2:

                            System.out.print(
                                    "Enter amount to deposit: "
                            );

                            double depositAmount =
                                    Double.parseDouble(
                                            input.nextLine()
                                    );

                            atm.deposit(
                                    account,
                                    depositAmount
                            );

                            System.out.printf(
                                    "New balance: PHP %.2f%n",
                                    account.getBalance()
                            );

                            break;

                        case 3:

                            System.out.print(
                                    "Enter amount to withdraw: "
                            );

                            double withdrawAmount =
                                    Double.parseDouble(
                                            input.nextLine()
                                    );

                            account.withdraw(
                                    withdrawAmount
                            );

                            System.out.printf(
                                    "Withdrew PHP %.2f. "
                                            + "New balance: PHP %.2f%n",
                                    withdrawAmount,
                                    account.getBalance()
                            );

                            break;

                        case 4:

                            double total =
                                    atm.depositAll(
                                            account,
                                            100,
                                            250.5,
                                            300
                                    );

                            System.out.printf(
                                    "Total deposited: PHP %.2f%n",
                                    total
                            );

                            System.out.printf(
                                    "New balance: PHP %.2f%n",
                                    account.getBalance()
                            );

                            break;

                        case 5:

                            System.out.printf(
                                    "Balance before: PHP %.2f%n",
                                    account.getBalance()
                            );

                            atm.tryToReplace(account);

                            System.out.printf(
                                    "After replacement attempt: PHP %.2f%n",
                                    account.getBalance()
                            );

                            atm.addBonus(
                                    account,
                                    50
                            );

                            System.out.printf(
                                    "After PHP 50 bonus: PHP %.2f%n",
                                    account.getBalance()
                            );

                            break;

                        case 0:

                            running = false;

                            break;

                        default:

                            System.out.println(
                                    "Invalid option. "
                                            + "Choose 0 to 5."
                            );
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Error: Please type a number, "
                                    + "not letters."
                    );

                } catch (InsufficientFundsException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );

                    System.out.printf(
                            "You need PHP %.2f more.%n",
                            e.getShortfall()
                    );

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );

                } finally {

                    System.out.println(
                            "---------------------------------"
                    );
                }
            }

            System.out.println(
                    "Thank you for using Liceo ATM!"
            );
        }
    }

    private static void printHeader(
            Account account) {

        System.out.println(
                "================================="
        );

        System.out.println(
                "       LICEO ATM MACHINE"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "Account : "
                        + account.getAccountNumber()
                        + " ("
                        + account.getOwnerName()
                        + ")"
        );

        System.out.println(
                "Type    : "
                        + account.getAccountType()
        );

        System.out.printf(
                "Balance : PHP %.2f%n",
                account.getBalance()
        );

        System.out.println(
                "---------------------------------"
        );
    }
}