package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem{

//constructor
    public Gum (String itemName, BigDecimal itemPrice) {
        super (itemName, itemPrice);

    }

// item message
    @Override
    public String itemMessage() {
        return "Chew Chew, Yum!";
    }
}
