package com.techelevator.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {

    @Test
    public void getMoneyProvided() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals(new BigDecimal("0.00"), vendingMachine.getMoneyProvided());

        vendingMachine.addToMoneyProvided(new BigDecimal("5.00"));
        assertEquals(new BigDecimal("5.00"), vendingMachine.getMoneyProvided());

        vendingMachine.subtractFromMoneyProvided(new BigDecimal("1.25"));
        assertEquals(new BigDecimal("3.75"), vendingMachine.getMoneyProvided());

        vendingMachine.subtractFromMoneyProvided(new BigDecimal("3.75"));
        assertEquals(new BigDecimal("0.00"), vendingMachine.getMoneyProvided());
    }
    // Please note: also tested addToMoneyProvided and subtractFromMoneyProvided here.
    // Manual testing did not allow purchases made with insufficient funds, no need to test negative values.

//    @Test
//    public void addToMoneyProvided() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }
//
//    @Test
//    public void subtractFromMoneyProvided() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }

//        *** Did not make test, as manual testing shows this method works as intended. ***
//    @Test
//    public void getInventory() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }

//        *** Did not make tests, as manual testing shows this method works as intended. ***
//    @Test
//    public void addFedMoneyToLog() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }
//
//    @Test
//    public void addPurchaseToLog() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }
//
//    @Test
//    public void addChangeReturnedToLog() {
//        VendingMachine vendingMachine = new VendingMachine();
//    }
}