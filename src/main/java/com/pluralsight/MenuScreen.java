package com.pluralsight;

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
        int count = 0;
        String header = "=";

        while (!choice.equalsIgnoreCase("X")) {

            System.out.println(" ");
            for (count = 0; count <= 20; count ++) {
                System.out.print(header);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Recheck line 30..");
                }
            }
            System.out.println("\nWelcome to GameVault!");

            for (count = 0; count <= 20; count ++) {
                System.out.print(header);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("Recheck line 30..");
                }
            }

            System.out.println(" ");
//            System.out.println(header);
//            System.out.println("Welcome to GameVault!");
//            System.out.println("========================");
            System.out.println("\n|---------------------|");
            System.out.println("|  P. Make a payment  |");
            System.out.println("|---------------------|");
            System.out.println("|  D. Make a deposit  |");
            System.out.println("|---------------------|");
            System.out.println("|  L. View the ledger |");
            System.out.println("|---------------------|");
            System.out.println("|  X. Exit me out     |");
            System.out.println("|---------------------|");

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
        System.out.println("Launching Deposits..\n");
        int count;
        String dollar = "|";

        for (count = 0; count <= 20; count++) {
            System.out.print(dollar);

            if (count == 10) {
                System.out.print("Money Long");
            }

            try {
                Thread.sleep(150);
            } catch (Exception e) {
                System.out.println("System error. Please check line 97..");
            }
        }

        System.out.println("\nDeposit loaded successfully!");

        System.out.println("\nWhat did the consumer purchase?");
        String description = read.nextLine();

        System.out.println("Which vendor distributes this product?");
        String vendor = read.nextLine();

        System.out.println("How much did we receive from this transaction?");

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

        System.out.println("Payments loading...");
        int count = 0;
        String header = "/";

        for (count = 0; count <= 20; count ++) {
            System.out.print(header);

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("Error doing this here.");
            }

        }

        System.out.println("\nWrite the description for what we purchased: ");
        String description = read.nextLine();

        System.out.println("Which vendor did we purchase this product from?");
        String vendor = read.nextLine();

        System.out.println("How much was the product?");

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
            System.out.println("\n|---- Ledger Menu ----|");
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
                case "R" -> reportsMenu();
                case "H" -> {}
                default -> System.out.println("Invalid, please try again!");
            }
        }
    }
    private void reportsMenu() {
        String choice = "";

        while (!choice.equalsIgnoreCase("0")) {
            System.out.println("\n |---- Reports ----| ");
            System.out.println("1. Month to Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year to Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Vendor");
            System.out.println("0. Back to Ledger Screen");
            System.out.println("What would you like to see?");

            choice = read.nextLine();

            switch (choice.toUpperCase()) {
                case "1" -> ledger.displayMonthToDate();
                case "2" -> ledger.displayPreviousMonth();
                case "3" -> ledger.displayYearToDate();
                case "4" -> ledger.displayPreviousYear();
                case "5" -> searchByVendor();
                case "0" -> {}
                default -> System.out.println("Please type something valid!");
            }

        }
    }

    // This will allow the user to search for vendors. This ties in with our displayTransactionsByVendor in Ledgar.
    public void searchByVendor() {
        System.out.println("Enter the desired Vendor name: ");
        String searchVendor = read.nextLine().trim();

        if (searchVendor.isEmpty()) {
            System.out.println("Please enter a vendor name.");
            return;
        }

        ledger.displayTransactionsByVendor(searchVendor);
    }
}
