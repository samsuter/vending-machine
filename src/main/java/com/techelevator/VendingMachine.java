package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachine {

    // Console utilities (fun colors, etc)
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_LIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\033[0;35m";


    public String getItemSelection() {
        return itemSelection;
    }

    // Instance variables
    private Map<String, VendingMachineItem> itemMap;
    private String itemSelection = null;
    private BigDecimal curMoney = new BigDecimal("0");
    private BigDecimal userFeedMoney = new BigDecimal("0");
    private BigDecimal quarter = new BigDecimal("0.25");
    private BigDecimal dime = new BigDecimal((".10"));
    private BigDecimal nickel = new BigDecimal(".05");

    public BigDecimal getCurMoney() {
        return curMoney;
    }

    private BigDecimal penny = new BigDecimal(".01");

    public BigDecimal getUserFeedMoney() {
        return userFeedMoney;
    }

    public void setCurMoney(BigDecimal curMoney) {
        this.curMoney = curMoney;
    }

    // Setters
    public void setItemSelection(String itemSelection) {
        this.itemSelection = itemSelection;
    }

    public void setUserFeedMoney(BigDecimal userFeedMoney) {
        this.userFeedMoney = userFeedMoney;
    }

    // Getters
    public Map<String, VendingMachineItem> getItemMap() {
        return itemMap;
    }

    private Map<String, VendingMachineItem> itemPopulate() {
        File vendingMachineList = new File("vendingmachine.csv");
        Map<String, VendingMachineItem> itemMap = new TreeMap<>();

        try (Scanner scanner = new Scanner(vendingMachineList)) {
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] itemArray = currentLine.split("\\|");
                VendingMachineItem item = null;
                String itemKey = null;
                if (itemArray[3].equals("Candy")) {
                    itemKey = itemArray[0];
                    item = new Candy(itemArray[1], BigDecimal.valueOf(Double.parseDouble(itemArray[2])));
                } else if (itemArray[3].equals("Chip")) {
                    itemKey = itemArray[0];
                    item = new Chips(itemArray[1], BigDecimal.valueOf(Double.parseDouble(itemArray[2])));
                } else if (itemArray[3].equals("Drink")) {
                    itemKey = itemArray[0];
                    item = new Drinks(itemArray[1], BigDecimal.valueOf(Double.parseDouble(itemArray[2])));
                } else if (itemArray[3].equals("Gum")) {
                    itemKey = itemArray[0];
                    item = new Gum(itemArray[1], BigDecimal.valueOf(Double.parseDouble(itemArray[2])));
                }
                itemMap.put(itemKey, item);
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return itemMap;
    }


    // The first menu that the customer will see upon startup.
    public void firstMenuDisplay() {
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "Main Menu" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(1) Display Vending Machine Items" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(2) Purchase" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(3) Exit" + ANSI_RESET);
        System.out.println();
        System.out.print(ANSI_BOLD + ANSI_LIGHT_CYAN + "Please select by number: " + ANSI_RESET);
    }


    // The customer will see this menu if they press "2" for purchase.
    public void purchaseMenuDisplay() {
        System.out.println();
        System.out.println(ANSI_BOLD + ANSI_BLUE + "Current Money Provided: " + "$" + curMoney + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "Purchase Menu" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(1) Feed Money" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(2) Select Product" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "(3) Finish Transaction" + ANSI_RESET);
        System.out.println();
        System.out.print(ANSI_BOLD + ANSI_LIGHT_CYAN + "Please select by number: " + ANSI_RESET);
    }

    // Methods

    public VendingMachine() {
        this.itemMap = itemPopulate();
    }

    // Feed money method will intake money from customer and add as each bill is inserted.
    // Error message will occur if customer doesn't enter full dollar amounts.
    public BigDecimal feedMoney() {
        if (userFeedMoney.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0 &&
                (userFeedMoney.compareTo(BigDecimal.ZERO) >= 0)) {
            this.curMoney = curMoney.add(userFeedMoney);
        } else {
            System.out.print(ANSI_RED + ANSI_BOLD + "Please enter only in full dollar amounts." + ANSI_RESET);
        } return curMoney;
    }

    // Purchase item method will let customer know if item exists, is in stock, or if customer has inserted
    // enough money for the purchase.
    public void inventoryPrint() {
        System.out.println();
        for (String curKey : itemMap.keySet()) {
            System.out.println(ANSI_PURPLE + curKey + ") " + itemMap.get(curKey).getItemName() + ", " + "$" + itemMap.get(curKey).getItemPrice() + ANSI_RESET);
        }
    }

    // Purchase item prompt method will ask customer to enter product ID (aka slot number).
    public void purchaseItemPrompt() {
        System.out.println();
        inventoryPrint();
        System.out.println();
        System.out.print(ANSI_LIGHT_CYAN + ANSI_BOLD + "Please enter product ID:" + ANSI_RESET);
    }

    public void purchaseItem() {
        if (!itemMap.containsKey(itemSelection)) {
            System.out.println(ANSI_RED + ANSI_BOLD + "Item does not exist." + ANSI_RESET);
        } else if (itemMap.get(itemSelection).getItemPrice().compareTo(curMoney) > 0) {
            System.out.println(ANSI_RED + ANSI_BOLD + "Not enough money." + ANSI_RESET);
        } else if (itemMap.get(itemSelection).getItemStock() <= 0) {
            System.out.println(ANSI_RED + ANSI_BOLD + "Sold Out" + ANSI_RESET);
        }
        // If all conditions are met, then the product will be dispensed to customer. Corresponding
        // item message will appear. Item stock will also be updated.
        else {
            curMoney = curMoney.subtract(itemMap.get(itemSelection).getItemPrice());
            itemMap.get(itemSelection).setItemStock(itemMap.get(itemSelection).getItemStock() - 1);
            System.out.println();
            System.out.println(ANSI_BOLD + ANSI_GREEN + "Item name: " + itemMap.get(itemSelection).getItemName() + ANSI_RESET);
            System.out.println(ANSI_BOLD + ANSI_GREEN + "Item price: " + itemMap.get(itemSelection).getItemPrice() + ANSI_RESET);
            System.out.println(ANSI_BOLD + ANSI_GREEN + "Remaining money: " + curMoney + ANSI_RESET);
            System.out.println(ANSI_BOLD + ANSI_PURPLE + itemMap.get(itemSelection).itemMessage() + ANSI_RESET);
        }
    }

    public int[] getChange() {
        int numOfQuartersInt = 0;
        int numOfDimesInt = 0;
        int numOfNickelsInt = 0;
        int numOfPenniesInt = 0;
        int[] allTheChange = {numOfQuartersInt, numOfDimesInt, numOfNickelsInt, numOfPenniesInt};

        if (curMoney.divide(quarter).compareTo(BigDecimal.ONE) >= 0) {
            BigDecimal numOfQuarters = curMoney.divide(quarter, RoundingMode.HALF_DOWN);
            numOfQuartersInt = numOfQuarters.intValue();
            allTheChange[0] = numOfQuartersInt;
            curMoney = curMoney.subtract(quarter.multiply(BigDecimal.valueOf(numOfQuartersInt)));
            System.out.println("Quarters: " + numOfQuartersInt);
        } if (curMoney.divide(dime).compareTo(BigDecimal.ONE) >= 0) {
            BigDecimal numOfDimes = curMoney.divide(dime, RoundingMode.HALF_DOWN);
            numOfDimesInt = numOfDimes.intValue();
            allTheChange[1] = numOfDimesInt;
            curMoney = curMoney.subtract(dime.multiply(BigDecimal.valueOf(numOfDimesInt)));
            System.out.println("Dimes: " + numOfDimesInt);
        } if (curMoney.divide(nickel).compareTo(BigDecimal.ONE) >= 0) {
            BigDecimal numOfNickels = curMoney.divide(nickel, RoundingMode.HALF_DOWN);
            numOfNickelsInt = numOfNickels.intValue();
            allTheChange[2] = numOfNickelsInt;
            curMoney = curMoney.subtract(nickel.multiply(BigDecimal.valueOf(numOfNickelsInt)));
            System.out.println("Nickels: " + numOfNickelsInt);
        } if (curMoney.divide(penny).compareTo(BigDecimal.ONE) >= 0) {
            BigDecimal numOfPennies = curMoney.divide(penny, RoundingMode.HALF_DOWN);
            numOfPenniesInt = numOfPennies.intValue();
            allTheChange[3] = numOfPenniesInt;
            curMoney = curMoney.subtract(penny.multiply(BigDecimal.valueOf(numOfPenniesInt)));
            System.out.println("Pennies: " + numOfPenniesInt);
        } return allTheChange;
    }
}





