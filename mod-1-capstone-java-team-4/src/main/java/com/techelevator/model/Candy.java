package com.techelevator.model;

public class Candy extends VendingMachineItem {

    public Candy(String productCode, String productName, String productPrice, int quantity) {
        super(productCode, productName, productPrice, "Candy", quantity);
    }
}
