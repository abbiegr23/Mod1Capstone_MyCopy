package com.techelevator.model;

public class Drink extends VendingMachineItem {

    public Drink(String productCode, String productName, String productPrice, int quantity) {
        super(productCode, productName, productPrice, "Drink", quantity);
    }

}
