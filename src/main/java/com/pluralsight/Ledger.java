package com.pluralsight;

import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;

public class Ledger {

    private ArrayList<Transactions> transactions = new ArrayList<>(); // creating this to handle transaction history

//    This is so the system still remembers all the transactions.
    private double totalDepositAmount = 0;

    public void addDeposit(String description, String vendor, double amount) { //receiving information from the creation of the object
        if (amount > 0 && Double.isFinite(amount)) {

            Transactions deposit = new Transactions(description, vendor, amount);

            transactions.add(deposit); // adding the current transaction call to deposits.
            totalDepositAmount += amount; // we're updating the amount as we go.

            System.out.println("Deposit accepted: $" + amount);
            System.out.printf("We're currently at $%.2f in total", totalDepositAmount);
        } else {
            System.out.println("This deposit amount is invalid.");
        }
    }

    // future development of the transactions list.
    public void displayTransactions() {

        System.out.println("Ledger Information");
//      this will show all the transactions within the csv file.
        for (Transactions transactions : transactions) { // for Transaction class, transactions object in transactions objects
            System.out.printf("%s | %s | %s | $%.2f%n", transactions.getFormattedDateTime(),
                    transactions.getDescription(),
                    transactions.getVendor(),
                    transactions.getAmount());
        }
    }

    public void readTransactionsFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Transactions.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("Is there something here..?");
        }
    }
}
