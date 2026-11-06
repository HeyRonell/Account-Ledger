package com.pluralsight;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        Ledger ledger = new Ledger(); // creating a new Ledger object named ledger.

        String dot = ".";


//        Load the csv file here so we don't read it anymore causing dupes.
        ledger.readTransactionsFile();

        System.out.println("\nLoading please wait..");
        for (int count = 0; count <= 10; count ++) {
            System.out.print(dot);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                System.out.println("This is not working");
            }
        }
        System.out.println(" ");
        System.out.println("\nLoad successful!\n");

        MenuScreen ui = new MenuScreen(read, ledger); // calling on the scanner and ledger objects as params
        ui.displayMenu(); //Bringing up the menu screen method.

        read.close();
    }
}

