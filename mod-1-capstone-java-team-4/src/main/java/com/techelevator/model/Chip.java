package com.techelevator.model;

public class Chip extends VendingMachineItem {

    public Chip(String productCode, String productName, String productPrice, int quantity) {
        super(productCode, productName, productPrice, "Chip", quantity);
    }

}
