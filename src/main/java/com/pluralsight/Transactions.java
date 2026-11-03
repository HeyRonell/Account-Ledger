package com.pluralsight;

import java.time.LocalDate;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Transactions {
    private String description;
    private String vendor;
    private double amount;
    private LocalDateTime dateTime;

//    making this paramaterized constructor for future objects.
    public Transactions(String description, String vendor, double amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public Transactions() {
        description = "";
        vendor = "";
        amount = 0.0;
    }
//  this constructor is used for older items on the list.
    public Transactions(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

//    Establishing the format for the time and returning our new variable.
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }
}
