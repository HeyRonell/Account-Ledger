package com.pluralsight;

import java.util.Locale;
import java.util.Scanner;

public class MenuScreen {

    private Ledger ledger;
    private Scanner read;

    public MenuScreen(Scanner read, Ledger ledger) {
        this.read = read; // making sure this is pulled from main to be reused here.
        this.ledger = ledger; // This is also getting passed from main to here
    }

//    Our main menu screen display
    public void displayMenu() {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {


            System.out.println("\nWelcome to GameVault!");
            System.out.println("P. Make a payment");
            System.out.println("D. Make a deposit");
            System.out.println("L. View the ledger");
            System.out.println("X. Exit me out");

            System.out.println("What would you like to do?");
            choice = read.nextLine();


            switch (choice.toUpperCase()) { //This will ensure the input outputs as uppercase.
                case "P":
                    paymentHandle(); // This will pull the payment method from below.
                    break;

                case "D":
                    depositHandle(); // handling deposits in the method below.
                    break;

                case "L":
                    ledgerMenu(); // pulling the ledger object information.
                    break;

                case "X":
                    System.out.println("Thank you for using GameVault!");
                    return;

                default:
                    System.out.println("Please revise your input and try again!");
                    break; // executes if the input is an invalid call.


            }

        }

    }

//      Methods to handle deposits/payments from the user.
    private void depositHandle() {
        System.out.println("What is the description for the product?");
        String description = read.nextLine();

        System.out.println("Who is the vendor of the product?");
        String vendor = read.nextLine();

        System.out.println("How much would you like to deposit?");

        try {
            double amount = Double.parseDouble(
                    read.nextLine());

            ledger.addDeposit(description, vendor, amount);
        } catch (NumberFormatException e) {
            System.out.println("This number is invalid. Please try again!");
        }
    }

//    Handling user inputs for payments
    private void paymentHandle() {
        System.out.println("Describe the product you are purchasing: ");
        String description = read.nextLine();

        System.out.println("Who are you purchasing this product from?");
        String vendor = read.nextLine();

        System.out.println("How much is the payment for this item?");

        try {
            double amount = Double.parseDouble(read.nextLine());

            ledger.addPayment(description, vendor, amount);
        } catch (NumberFormatException e) {
            System.out.println("This number is not valid. Please try again!");
        }
    }

//    Creating a submenu for the Ledger class to separate the transactions
    private void ledgerMenu() {
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A. All transactions");
            System.out.println("D. Deposits");
            System.out.println("P. Payments");
            System.out.println("B. Balance");
            System.out.println("R. Reports");
            System.out.println("H. Home");
            System.out.println("What would you like to do?");

            choice = read.nextLine();

            switch (choice.toUpperCase()) {
                case "A" -> ledger.displayTransactions();
                case "D" -> ledger.displayDeposits();
                case "P" -> ledger.displayPayments();
                case "B" -> ledger.displayBalance();
                case "R" -> System.out.println("Reports");
                case "H" -> {}
                default -> System.out.println("Invalid, please try again!");
            }
        }
    }
    private void reportsMenu() {
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("\n --- Reports --- ");
            System.out.println("1. Month to Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year to Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Vendor");
            System.out.println("H. Back to Ledger Screen");
            System.out.println("What would you like to see?");

            choice = read.nextLine();

            switch (choice.toUpperCase()) {
                case "1" -> System.out.println("Temp Writing");
                case "2" -> System.out.println("Temp Writing");
                case "3" -> System.out.println("Temp Writing");
                case "4" -> System.out.println("Temp Writing");
                case "5" -> System.out.println("Temp Writing");
                case "H" -> {}
                default -> System.out.println("Please type something valid!");
            }

        }
    }
}
