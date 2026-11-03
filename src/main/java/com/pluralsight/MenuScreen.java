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
    private void ledgerMenu() {
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A. All transactions");
            System.out.println("D. Deposits");
            System.out.println("P. Payments");
            System.out.println("R. Reports");
            System.out.println("H. Home");
            System.out.println("What would you like to do?");

            choice = read.nextLine();

            switch (choice.toUpperCase()) {
                case "A" -> ledger.displayTransactions();
                case "D" -> ledger.displayDeposits();
                case "P" -> ledger.displayPayments();
                case "R" -> System.out.println("Reports");
                case "H" -> System.out.println("Home");
                default -> System.out.println("Invalid, please try again!");
            }
        }
    }
}
