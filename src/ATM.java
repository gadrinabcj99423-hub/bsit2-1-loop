import java.util.Scanner;

class Account {


    private String owner;
    private double balance;


    public Account(String owner, double openingBalance) {
        this.owner = owner;


        if (openingBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }
    }


    public String getOwner() {
        return owner;
    }


    public double getBalance() {
        return balance;
    }


    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
    }


    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance: " + balance);
    }
}

public class ATM {

    public static void main(String[] args) {


        try (Scanner input = new Scanner(System.in)) {

            Account account = new Account("Juan Dela Cruz", 1000.0);

            boolean running = true;

            while (running) {
                String List = """
                    
                        ====== WELCOME TO CLI ATM ======
                        
                        [1] Check Balance 
                        [2] Deposit
                        [3] Withdraw 
                        [4] Exit 
                        """;
                System.out.println(List);
                System.out.print("Please Enter Your Choices: ");

                int choice = input.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("Account Holder: " + account.getOwner());
                        System.out.println("Current Balance: " + account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = input.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        running = false;
                        System.out.println("Thank you for using CLI ATM!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}