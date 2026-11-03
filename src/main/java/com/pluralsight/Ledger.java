package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

            reader.readLine(); // This is to skip the header line and continue with the rest
            String line; // having a default value for the line.

            // starting off the while loop with the separation of parts.
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                LocalDate date1 = LocalDate.parse(parts[0]);
                LocalTime time1 = LocalTime.parse(parts[1]);

                String description1 = parts[2];
                String vendor1 = parts[3];
                double amount1 = Double.parseDouble(parts[4]);

//                Combining date and time
                LocalDateTime transactionDateTime = LocalDateTime.of(date1, time1);

//                Creating a new object to keep its original timestamp.
                Transactions transaction = new Transactions(
                        transactionDateTime, description1, vendor1, amount1
                );

               // Transactions transaction1 = new Transactions(description1, vendor1, amount1);
                this.transactions.add(transaction);

                System.out.println("---------------------------");

                System.out.println("Date: " + date1);
                System.out.println("Time: " + time1);
                System.out.println("Description: " + description1);
                System.out.println("Vendor: " + vendor1);
                System.out.println("Amount: " + amount1);

                System.out.println("---------------------------");

                System.out.println("Transactions loaded: " + transactions.size());

                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("Is there something here..?");
        }
    }
}
