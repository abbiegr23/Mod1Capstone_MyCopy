package com.techelevator.model;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class VendingMachineItemTest {

    @Test
    public void getProductType() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("A1");

        assertEquals("Chip", vendingMachineItem.getProductType());

    }

    @Test
    public void getProductName() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("A2");

        assertEquals("Stackers", vendingMachineItem.getProductName());
    }

    @Test
    public void getProductPrice() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("A3");

        assertEquals("2.75", vendingMachineItem.getProductPrice());
    }

    @Test
    public void getProductPriceBD() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("A4");

        assertEquals(new BigDecimal("3.65"), vendingMachineItem.getProductPriceBD());
    }

    @Test
    public void getProductCode() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("B1");

        assertEquals("B1", vendingMachineItem.getProductCode());
    }

    @Test
    public void getQuantity() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("B2");

        assertEquals(5, vendingMachineItem.getQuantity());

        vendingMachineItem.updateProductQuantity(1);
        assertEquals(4, vendingMachineItem.getQuantity());
    }

    @Test
    public void updateProductQuantity() throws IOException {
        VendingMachine vendingMachine = new VendingMachine();
        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();
        VendingMachineItem vendingMachineItem = inventoryList.get("A1");
        VendingMachineItem vendingMachineItem2 = inventoryList.get("B1");

        assertEquals(4, vendingMachineItem.updateProductQuantity(1));
        assertEquals(3, vendingMachineItem.updateProductQuantity(1));

        assertEquals(2, vendingMachineItem2.updateProductQuantity(3));
        assertEquals(0, vendingMachineItem2.updateProductQuantity(2));
        // manual testing shows that CLI will not allow you to buy more than 5, no need to test past 0.
    }


}