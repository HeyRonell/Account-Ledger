package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;

public class Ledger {

    private ArrayList<Transactions> transactions = new ArrayList<>(); // creating this to handle transaction history

//    This is so the system still remembers all the deposits made.
    private double totalDepositAmount = 0;

    public void addDeposit(String description, String vendor, double amount) { // adds to the array list if the csv file saves correctly.
        if (amount > 0 && Double.isFinite(amount)) {

            Transactions deposit = new Transactions(description, vendor, amount);

            boolean saved = savingTransactions(deposit);

            if (saved) {
                transactions.add(deposit); // adding the current transaction call to deposits.
                totalDepositAmount += amount; // we're updating the amount as we go.
                System.out.printf("Deposit accepted: $%.2f%n", amount);
                System.out.printf("We're currently at $%.2f in total", totalDepositAmount);
            } else {
                System.out.println("we were unable to save that transaction on file.");
            }

        } else {
            System.out.println("This deposit amount is invalid.");
        }
    }

    public void addPayment(String description, String vendor, double amount) {
        if (amount > 0 && Double.isFinite(amount)) {
            double paymentAmount = -amount;
            Transactions payment = new Transactions(description, vendor, paymentAmount);

            boolean saved = savingTransactions(payment);

            if (saved) {
                transactions.add(payment);

                System.out.printf("Payment recorded: $%.2f%n", paymentAmount);
            } else {
                System.out.println("Payment was not processed properly.");
            }
        } else {
            System.out.println("Invalid payment amount.");
        }

    }
    public double calculateBalance() {
        double balance = 0.0;

        for (Transactions transaction : transactions) {
            balance += transaction.getAmount();

        }
        return balance;
    }

    // Displays for Ledger submenu
    public void displayTransactions() {

        System.out.println("Ledger Information");
//      this will show all the transactions within the csv file.
        for (Transactions transactions : transactions) { // in the array list Transactions, go through each transactions object one at a time.
            System.out.printf("%s | %s | %s | $%.2f%n", transactions.getFormattedDateTime(),
                    transactions.getDescription(),
                    transactions.getVendor(),
                    transactions.getAmount());
        }
    }
    public void displayDeposits() {
        System.out.println("\n ---- Deposits ----");

        for (Transactions transaction : transactions) {

            if (transaction.getAmount() > 0) {
                System.out.printf("%s %s %s $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    public void displayPayments() {
        System.out.println("\n---- Payments ----");

        for (Transactions transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.printf("%s %s %s $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    public void displayBalance() {
        double balance = calculateBalance();

        System.out.printf("Current balance: $%.2f%n", balance);
    }

//    Displays for reports
    public void displayMonthToDate() {
        LocalDate today = LocalDate.now(); // todays date.
        LocalDate firstDay = today.withDayOfMonth(1); // determines if the day is on or after the first day of the month

        System.out.println("\n ---- Month To Date ----");

        for (Transactions transaction : transactions) {
            LocalDate transactionDate = transaction.getDateTime().toLocalDate();

            // is the date on or after the first day and before the current day.
            if (!transactionDate.isBefore(firstDay) && !transactionDate.isAfter(today)) {

                System.out.printf("%s | %s | %s | $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());

            }
        }
    }

    public void displayPreviousMonth() {
        LocalDate today = LocalDate.now();

        // .minusMonths allows us to subtract by a month, and the DayOfMonth sets the start date to the first day of the month.
        LocalDate firstDayOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
        // this will identify the day pertaining to the month prior to the current.
        LocalDate lastDayOfPreviousMonth = today.withDayOfMonth(1).minusDays(1);

        System.out.println("\n--- Previous Month Report ---");

        for (Transactions transaction : transactions) {

            LocalDate transactionDate = transaction.getDateTime().toLocalDate();
//            We're checking to see if the date is after the first day of last month and before last day of last month.
            if (!transactionDate.isBefore(firstDayOfPreviousMonth) && !transactionDate.isAfter(lastDayOfPreviousMonth)) {

                System.out.printf("%s | %s | %s | $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    public void displayYearToDate() {
        LocalDate today = LocalDate.now();

//        Finding the first day of the current year.
        LocalDate firstDayOfYear = today.withDayOfYear(1);

        System.out.println("\n--- Yearly Report ---");

        for (Transactions transaction : transactions) {
            LocalDate transactionDate = transaction.getDateTime().toLocalDate();
//        This is checking from january 1st to the current day. Not whole year.
            if (!transactionDate.isBefore(firstDayOfYear) && !transactionDate.isAfter(today)) {

                System.out.printf("%s | %s | %s | $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }
    public void displayPreviousYear() {
        LocalDate today = LocalDate.now(); // grabbing the date for today

//        subtracting a year and locating the first day of the year.
        LocalDate firstDayOfPreviousYear = today.minusYears(1).withDayOfYear(1);

//        January first of this year - 1 to get December 31st of the previous year.
        LocalDate lastDayOfPreviousYear = today.withDayOfYear(1).minusDays(1);

        System.out.println("\n --- Previous Yearly Report ---");

        for (Transactions transaction : transactions) {

            LocalDate transactionDate = transaction.getDateTime().toLocalDate();

            if (!transactionDate.isBefore(firstDayOfPreviousYear) && !transactionDate.isAfter(lastDayOfPreviousYear)) {
                System.out.printf("%s | %s | %s | $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }
    public void displayTransactionsByVendor(String searchVendor) {

        System.out.println("\n--- Vendor Search ---");
//        Again checking each object within the list
        for (Transactions transaction : transactions) {
//            if the vendor matches the users search, it will print out those.
            if (transaction.getVendor().equalsIgnoreCase(searchVendor)) {

                System.out.printf("%s | %s | %s | $%.2f%n",
                        transaction.getFormattedDateTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
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

    // calling bool to confirm with addDeposit method that the transaction saved to the csv as well.
    public boolean savingTransactions(Transactions transactions) { // csv file saves
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Transactions.csv", true))) {
//            getting the date set up
            String date = transactions.getDateTime().toLocalDate().toString();
//            getting the time set up
            String time = transactions.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//            putting it together
            writer.write(date + "|" +
                    time + "|" +
                    transactions.getDescription() + "|" +
                    transactions.getVendor() + "|" +
                    transactions.getAmount());

            writer.newLine(); // this will save our new transaction above with the format that we laid out.

            System.out.println("Save successful!");
            return true;

        } catch (IOException e) {
            System.out.println("There was an error with the file.");
        }
        return false;
    }
}
