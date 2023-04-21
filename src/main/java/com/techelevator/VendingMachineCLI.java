package com.techelevator;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    // Use this for keyboard input - it is initialized in the constructor
    private Scanner userInput;
    private VendingMachine vendingMachine;

    public VendingMachineCLI(Scanner userInput) {
        this.userInput = userInput;
        this.vendingMachine = new VendingMachine();
    }


    // Console utilities (colors, etc)
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_LIGHT_CYAN = "\u001B[96m";
    public static final String PURPLE = "\033[0;35m";


    // Everything in the program will run in this block
    public void run() {
        VendingMachine vendingMachine = new VendingMachine();
        Logger ourLogger = new Logger();
        ourLogger.freshLog(vendingMachine);



        vendingMachine.firstMenuDisplay();
        String MenuChoice = userInput.nextLine();

        // Instance variables
        boolean on = true;
        boolean inPurchaseMode = true;

        // The menu selection process and what happens if a customer selects each number.
        while (on) {

            if (MenuChoice.equals("1")) {
                vendingMachine.inventoryPrint();
                System.out.println();
                vendingMachine.firstMenuDisplay();
                MenuChoice = userInput.nextLine();
            } else if (MenuChoice.equals("2")) {
                vendingMachine.purchaseMenuDisplay();
                MenuChoice = userInput.nextLine();
                while (inPurchaseMode) {
                    if (MenuChoice.equals("1")) {
                        System.out.println();
                        System.out.print(ANSI_BOLD + ANSI_LIGHT_CYAN + "Please enter money amount: " + ANSI_RESET);
                        BigDecimal value = new BigDecimal(userInput.nextLine());
                        vendingMachine.setUserFeedMoney(value);
                        vendingMachine.feedMoney();
                        ourLogger.logFeedMoney(vendingMachine);
                        vendingMachine.purchaseMenuDisplay();
                        MenuChoice = userInput.nextLine();
                    } else if (MenuChoice.equals("2")) {
                        vendingMachine.purchaseItemPrompt();
                        vendingMachine.setItemSelection(userInput.nextLine());
                        vendingMachine.purchaseItem();
                        ourLogger.logPurchase(vendingMachine);
                        vendingMachine.purchaseMenuDisplay();
                        MenuChoice = userInput.nextLine();
                    } else if (MenuChoice.equals("3")) {
                        System.out.println("Your change is: ");
                        ourLogger.logMakeChange1(vendingMachine);
                        vendingMachine.getChange();
                        ourLogger.logMakeChange2(vendingMachine);
                        System.out.println("Bon Appetit!");
                        inPurchaseMode = false;
                        System.out.println();
                        vendingMachine.firstMenuDisplay();
                        MenuChoice = userInput.nextLine();
                    } else {
                        System.out.println("Invalid Entry");
                        vendingMachine.purchaseMenuDisplay();
                        MenuChoice = userInput.nextLine();
                    }
                }
                // If none of the above options are selected, condition won't be met and program will exit.
            } else if (MenuChoice.equals("3")) {
                on = false;
            } else {
                System.out.println("Invalid Entry");
                System.out.println();
                vendingMachine.firstMenuDisplay();
                MenuChoice = userInput.nextLine();
            }

        }
    }

    // Add a loop here for your menu
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        VendingMachineCLI cli = new VendingMachineCLI(input);
        cli.run();
    }
}




