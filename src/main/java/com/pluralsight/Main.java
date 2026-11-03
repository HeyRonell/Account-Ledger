package com.pluralsight;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        Ledger ledger = new Ledger(); // creating a new Ledger object named ledger.


//        Load the csv file here so we don't read it anymore causing dupes.
        ledger.readTransactionsFile();

        MenuScreen ui = new MenuScreen(read, ledger); // calling on the scanner and ledger objects as params
        ui.displayMenu(); //Bringing up the menu screen method.

        read.close();
    }
}

