package com.techelevator;

import java.math.BigDecimal;

public class Chips extends VendingMachineItem{

//constructor
    public Chips (String itemName, BigDecimal itemPrice) {
        super (itemName, itemPrice);

    }

// item message
    @Override
    public String itemMessage() {
        return "Crunch Crunch, Yum!";
    }
}

