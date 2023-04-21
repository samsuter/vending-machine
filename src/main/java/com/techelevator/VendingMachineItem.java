package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItem {

    // Instance variables
    private String itemName;
    private BigDecimal itemPrice;
    private int itemStock = 5;
    //private String slotNumber;


    // Constructor
    public VendingMachineItem (String itemName, BigDecimal itemPrice) {
        this.itemName = itemName;
//      this.slotNumber = slotNumber;
        this.itemPrice = itemPrice;
    }

    // Setters
    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    //    public String getSlotNumber() {
//        return slotNumber; }


    //Methods
    // When this method is called, it will display the item message for corresponding item.
    public abstract String itemMessage ();


}
