package com.techelevator.model;

public class Gum extends VendingMachineItem{

    public Gum(String productCode, String productName, String productPrice, int quantity) {
        super(productCode, productName, productPrice, "Gum", quantity);
    }
}
