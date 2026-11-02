package com.pluralsight;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        Ledger ledger = new Ledger(); // creating a new Ledger object named ledger.
        MenuScreen ui = new MenuScreen(read, ledger); // calling on the scanner and ledger objects as params
        ui.displayMenu();

        read.close();
    }
}

