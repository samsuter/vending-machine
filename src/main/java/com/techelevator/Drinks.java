package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends VendingMachineItem {

    //constructor
    public Drinks (String itemName, BigDecimal itemPrice) {
            super (itemName, itemPrice);

        }

// item message
        @Override
        public String itemMessage() {
            return "Glug Glug, Yum!";
        }
}
