package com.pluralsight;

public class Ledger {

//    This is so the system still remembers all the transactions.
    private double totalDepositAmount = 0;

    public void addDeposit(double amount) {
        if (amount > 0 && Double.isFinite(amount)) {
            totalDepositAmount += amount; // we're updating the amount as we go.

            System.out.println("Deposit accepted: $" + amount);
            System.out.printf("We're currently at $%.2f in total", totalDepositAmount);
        } else {
            System.out.println("This deposit amount is invalid.");
        }
    }
}
