package com.techelevator.model;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {

    private Map<String, VendingMachineItem> inventoryList;
    private BigDecimal moneyProvided = new BigDecimal("0.00");

    public BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public BigDecimal addToMoneyProvided(BigDecimal fedMoney) {
        moneyProvided = moneyProvided.add(fedMoney);
        return moneyProvided;
    }

    public BigDecimal subtractFromMoneyProvided(BigDecimal subtractedMoney) {
        moneyProvided = moneyProvided.subtract(subtractedMoney);
        return moneyProvided;
    }

    public Map<String, VendingMachineItem> getInventory() throws IOException {

        File inventoryFile = new File("vendingmachine.csv");
        Map<String, VendingMachineItem> inventoryList = null;
        try (Scanner fileScanner = new Scanner(inventoryFile)) {

            inventoryList = new TreeMap<>();

            while (fileScanner.hasNextLine()) {
                String lineFromFile = fileScanner.nextLine();
                String[] partsOfLine = lineFromFile.split("\\|");

                if (partsOfLine[3].equals("Chip")) {
                    VendingMachineItem item = new Chip(partsOfLine[0], partsOfLine[1], partsOfLine[2], 5);
                    inventoryList.put(partsOfLine[0], item);
                } else if (partsOfLine[3].equals("Drink")) {
                    VendingMachineItem item = new Drink(partsOfLine[0], partsOfLine[1], partsOfLine[2],5);
                    inventoryList.put(partsOfLine[0], item);
                } else if (partsOfLine[3].equals("Candy")) {
                    VendingMachineItem item = new Candy(partsOfLine[0], partsOfLine[1], partsOfLine[2], 5);
                    inventoryList.put(partsOfLine[0], item);
                } else if (partsOfLine[3].equals("Gum")) {
                    VendingMachineItem item = new Gum(partsOfLine[0], partsOfLine[1], partsOfLine[2], 5);
                    inventoryList.put(partsOfLine[0], item);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return inventoryList;
    }


    public void addFedMoneyToLog(BigDecimal fedMoney) throws IOException {
        File transactionLogFile = new File("Log.txt");

        if (!transactionLogFile.exists()) {
            transactionLogFile.createNewFile();
        }

        Date transactionTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String actionLog = " FEED MONEY: $";
        String fedMoneyString = fedMoney.toString() + " $";
        String moneyProvidedString = getMoneyProvided().toString();

        try ( FileOutputStream stream = new FileOutputStream(transactionLogFile, true);
                PrintWriter writer = new PrintWriter(stream)) {
            writer.append(formatter.format(transactionTime) );
            writer.append(actionLog );
            writer.append(fedMoneyString);
            writer.append(moneyProvidedString);
            writer.append(System.lineSeparator());
        }
    }

    public void addPurchaseToLog(VendingMachineItem aVendingMachineItem) throws IOException {
        File transactionLogFile = new File("Log.txt");

        if (!transactionLogFile.exists()) {
            transactionLogFile.createNewFile();
        }

        Date transactionTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String actionLog = " PURCHASE: " + aVendingMachineItem.getProductName() + " " + aVendingMachineItem.getProductCode() + " $" + aVendingMachineItem.getProductPrice();
        String moneyProvidedString = " $" + getMoneyProvided().toString();

        try ( FileOutputStream stream = new FileOutputStream(transactionLogFile, true);
              PrintWriter writer = new PrintWriter(stream)) {
            writer.append(formatter.format(transactionTime) );
            writer.append(actionLog );
            writer.append(moneyProvidedString);
            writer.append(System.lineSeparator());
        }
    }

    public void addChangeReturnedToLog(BigDecimal change) throws IOException {
        File transactionLogFile = new File("Log.txt");

        if (!transactionLogFile.exists()) {
            transactionLogFile.createNewFile();
        }

        Date transactionTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String actionLog = " CHANGE RETURNED TO CUSTOMER: $" + change;
        String moneyProvidedString = " $" + (getMoneyProvided().subtract(change)).toString();
        String endOfTransaction = "****END OF TRANSACTION****";

        try ( FileOutputStream stream = new FileOutputStream(transactionLogFile, true);
              PrintWriter writer = new PrintWriter(stream)) {
            writer.append(formatter.format(transactionTime) );
            writer.append(actionLog );
            writer.append(moneyProvidedString);
            writer.append(System.lineSeparator());
            writer.append(endOfTransaction);
            writer.append(System.lineSeparator());
            writer.append(System.lineSeparator());
        }
    }

}
