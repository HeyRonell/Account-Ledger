package com.pluralsight;

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


            System.out.println("Welcome to GameVault!");
            System.out.println("P. Make a payment");
            System.out.println("D. Make a deposit");
            System.out.println("View the ledger");
            System.out.println("X. Exit me out");

            System.out.println("What would you like to do?");
            choice = Main.read.nextLine();


            switch (choice.toUpperCase()) { //This will ensure the input outputs as uppercase.
                case "P":
                    System.out.println("Taking you to the payment..."); // this is temp.
                    break;

                case "D":
                    depositHandle();
                    break;

                case "L":
                    System.out.println("Taking you to the ledger now..."); // this is temp
                    break;

                case "X":
                    System.out.println("Thank you for using GameVault!");
                    return;

                default:
                    System.out.println("Please revise your input and try again!");
                    break;


            }

        }

    }

    private void depositHandle() {
        System.out.println("How much would you like to deposit?");

        try {
            double amount = Double.parseDouble(read.nextLine());
            ledger.addDeposit(amount);
        } catch (NumberFormatException e) {
            System.out.println("This number is invalid. Please try again!");
        }
    }
}
