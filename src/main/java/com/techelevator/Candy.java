package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem{


    //constructor
    public Candy (String itemName, BigDecimal itemPrice) {
        super (itemName, itemPrice);

    }

    // item message
    @Override
    public String itemMessage() {
        return "Munch Munch, Yum!";
    }
}


