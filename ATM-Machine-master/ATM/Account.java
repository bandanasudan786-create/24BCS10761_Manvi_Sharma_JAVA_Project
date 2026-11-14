
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account 
{
    private int customerNumber;
    private int pinNumber;
    private double checkingBalance;
    private double savingBalance;
    private final DecimalFormat moneyFormat=new DecimalFormat("'$'###,##0.00");

    public Account() 
	{
		
    }

    public Account(int customerNumber, int pinNumber) 
	{
        this.customerNumber= customerNumber;
        this.pinNumber= pinNumber;
    }

    public Account(int customerNumber, int pinNumber,double checkingBalance,double savingBalance) 
	{
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public void setCustomerNumber(int customerNumber) 
	{
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() 
	{
        return customerNumber;
    }

    public void setPinNumber(int pinNumber) 
	{
        this.pinNumber = pinNumber;
    }

    public int getPinNumber() 
	{
        return pinNumber;
    }

    public double getCheckingBalance() 
	{
        return checkingBalance;
    }

    public double getSavingBalance() 
	{
        return savingBalance;
    }

    public void withdrawChecking(double amount)
	{
        checkingBalance -= amount;
    }

    public void depositChecking(double amount)
	{
        checkingBalance += amount;
    }

    public void withdrawSavings(double amount) 
	{
        savingBalance -= amount;
    }

    public void depositSavings(double amount) 
	{
        savingBalance += amount;
    }

    public void transferToSavings(double amount) 
	{
        checkingBalance -= amount;
        savingBalance += amount;
    }

    public void transferToChecking(double amount) 
	{
        savingBalance -= amount;
        checkingBalance += amount;
    }

    private double getAmount(Scanner input) 
	{
        while(true) 
		{
            try 
			{
                double amount = input.nextDouble();
                if (Double.isFinite(amount) && amount > 0) 
				{
                    return amount;
                }
                System.out.println("Enter an amount greater than 0");
                System.out.print("Try again");

            } catch (InputMismatchException e) 
				{
                System.out.println("==Invalid input.Enter numbers only==");
                input.next();
                System.out.print("Try again");
            }
        }
    }

    public void checkingWithdraw(Scanner input) 
	{
        System.out.println("\nChecking Balance:"+moneyFormat.format(checkingBalance));
        System.out.print("Enter amount to withdraw: ");
        double amount = getAmount(input);
        if (amount <= checkingBalance) 
		{
            withdrawChecking(amount);
            System.out.println("Withdrawal successful!");
            System.out.println("Remaining Balance: "
                    + moneyFormat.format(checkingBalance));
        } 
		else 
		{
            System.out.println("Insufficient balance.");
        }
    }

    public void savingsWithdraw(Scanner input) 
	{
        System.out.println("\nSavings Balance:"+ moneyFormat.format(savingBalance));
        System.out.print("Enter amount to withdraw: ");
        double amount = getAmount(input);
        if (amount <= savingBalance) 
		{
            withdrawSavings(amount);
            System.out.println("Withdrawal successful!");
            System.out.println("Remaining Balance: "
                    + moneyFormat.format(savingBalance));
        } 
		else 
		{
            System.out.println("Insufficient balance.");
        }
    }

    public void checkingDeposit(Scanner input) 
	{
        System.out.print("Enter amount to deposit: ");
        double amount = getAmount(input);
        depositChecking(amount);
        System.out.println("Deposit successful");
        System.out.println("Updated Balance:"+ moneyFormat.format(checkingBalance));
    }
    public void savingsDeposit(Scanner input) {
        System.out.print("Enter amount to deposit: ");
        double amount = getAmount(input);

        depositSavings(amount);

        System.out.println("Deposit successful!");
        System.out.println("Updated Balance: "
                + moneyFormat.format(savingBalance));
    }

    public void transferMoney(String accountType, Scanner input) {

        if (accountType.equals("Checking")) {

            System.out.println("\n1. Transfer to Savings");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = getChoice(input);

            if (choice == 1) {
                System.out.print("Enter amount to transfer: ");
                double amount = getAmount(input);

                if (amount <= checkingBalance) {
                    transferToSavings(amount);

                    System.out.println("Transfer successful!");
                    System.out.println("Checking Balance: "
                            + moneyFormat.format(checkingBalance));
                    System.out.println("Savings Balance: "
                            + moneyFormat.format(savingBalance));
                } else {
                    System.out.println("Insufficient checking balance.");
                }

            } else if (choice != 2) {
                System.out.println("Invalid choice.");
            }

        } else if (accountType.equals("Savings")) {

            System.out.println("\n1. Transfer to Checking");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = getChoice(input);

            if (choice == 1) {
                System.out.print("Enter amount to transfer: ");
                double amount = getAmount(input);

                if (amount <= savingBalance) {
                    transferToChecking(amount);

                    System.out.println("Transfer successful!");
                    System.out.println("Savings Balance: "
                            + moneyFormat.format(savingBalance));
                    System.out.println("Checking Balance: "
                            + moneyFormat.format(checkingBalance));
                } else {
                    System.out.println("Insufficient savings balance.");
                }

            } else if (choice != 2) {
                System.out.println("Invalid choice.");
            }
        }
    }

    private int getChoice(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                input.next();
                System.out.print("Try again: ");
            }
        }
    }
}
