
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionMenu {

    private final Scanner input = new Scanner(System.in);
    private final DecimalFormat moneyFormat=new DecimalFormat("'$'###,##0.00");

    private final HashMap<Integer, Account> accounts = new HashMap<>();
    private int getNumber()
	{
        while (true) 
		{
            try 
			{
                return input.nextInt();
            } 
			catch (InputMismatchException e) 
			{
                System.out.println("Invalid input. Enter numbers only.");
                input.next();
                System.out.print("Try again: ");
            }
        }
    }

    public void login() {

        System.out.println("\n========== CUSTOMER LOGIN ==========");

        System.out.print("Enter Customer Number: ");
        int customerNumber = getNumber();

        System.out.print("Enter PIN: ");
        int pinNumber = getNumber();

        Account account = accounts.get(customerNumber);

        if (account != null && account.getPinNumber() == pinNumber) {
            System.out.println("\nLogin successful!");
            selectAccount(account);
        } else {
            System.out.println("\nIncorrect Customer Number or PIN.");
        }
    }

    public void selectAccount(Account account) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== ACCOUNT MENU ==========");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");

            int choice = getNumber();

            switch (choice) {
                case 1:
                    checkingMenu(account);
                    break;

                case 2:
                    savingsMenu(account);
                    break;

                case 3:
                    exit = true;
                    System.out.println("You have been logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void checkingMenu(Account account) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== CHECKING ACCOUNT ==========");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            int choice = getNumber();

            switch (choice) {
                case 1:
                    System.out.println("Checking Balance: "
                            + moneyFormat.format(account.getCheckingBalance()));
                    break;

                case 2:
                    account.checkingWithdraw(input);
                    break;

                case 3:
                    account.checkingDeposit(input);
                    break;

                case 4:
                    account.transferMoney("Checking", input);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void savingsMenu(Account account) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== SAVINGS ACCOUNT ==========");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            int choice = getNumber();

            switch (choice) {
                case 1:
                    System.out.println("Savings Balance: "
                            + moneyFormat.format(account.getSavingBalance()));
                    break;

                case 2:
                    account.savingsWithdraw(input);
                    break;

                case 3:
                    account.savingsDeposit(input);
                    break;

                case 4:
                    account.transferMoney("Savings", input);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void createAccount() {

        System.out.println("\n========== CREATE ACCOUNT ==========");

        System.out.print("Enter Customer Number: ");
        int customerNumber = getNumber();

        if (accounts.containsKey(customerNumber)) {
            System.out.println("Customer number already exists.");
            return;
        }

        System.out.print("Create a PIN: ");
        int pinNumber = getNumber();

        Account newAccount = new Account(customerNumber, pinNumber);

        accounts.put(customerNumber, newAccount);

        System.out.println("\nAccount created successfully!");
        System.out.println("You can now login using your details.");
    }

    public void mainMenu() {

        accounts.put(952141,
                new Account(952141, 191904, 1000, 5000));

        accounts.put(123,
                new Account(123, 123, 20000, 50000));

        boolean exit = false;

        while (!exit) {

            System.out.println("\n================================");
            System.out.println("           ATM MENU             ");
            System.out.println("================================");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getNumber();

            switch (choice) {
                case 1:
                    login();
                    break;

                case 2:
                    createAccount();
                    break;

                case 3:
                    exit = true;
                    System.out.println("\nThank you for using our ATM!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        input.close();
    }
}
