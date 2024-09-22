import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private String accountNumber;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: Rs. " + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: Rs. " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: Rs. " + amount);
            return true;
        }
        return false;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private BankAccount account;
    private String pin;

    public ATM(BankAccount bankAccount, String pin) {
        this.account = bankAccount;
        this.pin = pin;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (!enteredPin.equals(pin)) {
            System.out.println("Invalid PIN. Access denied.");
            return;
        }

        while (true) {
            displayMenu();
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance: Rs. " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful. Your balance: Rs. " + account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Your balance: Rs. " + account.getBalance());
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (String transaction : account.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    scanner.nextLine(); // Consume newline
                    String confirmExit = scanner.nextLine();
                    if (confirmExit.equalsIgnoreCase("yes")) {
                        System.out.println("Thank you for using the ATM!");
                        scanner.close();
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount("123456789", 1000); // Account number and initial balance
        ATM atm = new ATM(userAccount, "1234"); // Initialize ATM with a PIN
        atm.run();
    }
}
