package com.techelevator;

import com.techelevator.model.VendingMachine;
import com.techelevator.model.VendingMachineItem;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String FEED_MONEY = "Feed Money";
    private static final String SELECT_PRODUCT = "Select Product";
    private static final String FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] SUB_MENU_OPTIONS = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};
    private static final String FEED_ONE_DOLLAR = "Feed $1.00";
    private static final String FEED_FIVE_DOLLARS = "Feed $5.00";
    private static final String FEED_TEN_DOLLARS = "Feed $10.00";
    private static final String[] FEED_MONEY_MENU_OPTIONS = {FEED_ONE_DOLLAR, FEED_FIVE_DOLLARS, FEED_TEN_DOLLARS};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() throws IOException {

        boolean running = true;
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

                VendingMachineItem vmi = new VendingMachineItem();
                VendingMachine vendingMachine = new VendingMachine();
                //added

                Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();

                System.out.println("\n**** MENU OPTIONS: ****\n");
                for (Map.Entry<String, VendingMachineItem> entry : inventoryList.entrySet()) {
                    System.out.println("Item Code: " + entry.getKey() + " " + entry.getValue());
                }
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                VendingMachineItem vendingMachineItem = new VendingMachineItem(5);

                VendingMachine vendingMachine = new VendingMachine();
                boolean subRunning = true;
                while (subRunning) {
                    System.out.println("\n**** PURCHASE MENU ****\nCash Available To Spend: " + vendingMachine.getMoneyProvided());
                    String subMenuChoice = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);

                    if (subMenuChoice.equals(FEED_MONEY)) {

                        System.out.println("\nPlease select an amount to input:");
                        String feedMoneyChoices = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);
                            if (feedMoneyChoices.equals(FEED_ONE_DOLLAR)) {
                                vendingMachine.addToMoneyProvided(new BigDecimal("1.00"));
                                vendingMachine.addFedMoneyToLog(new BigDecimal("1.00"));
                            } else if (feedMoneyChoices.equals(FEED_FIVE_DOLLARS)) {
                                vendingMachine.addToMoneyProvided(new BigDecimal("5.00"));
                                vendingMachine.addFedMoneyToLog(new BigDecimal("5.00"));
                            } else if (feedMoneyChoices.equals(FEED_TEN_DOLLARS)) {
                                vendingMachine.addToMoneyProvided(new BigDecimal("10.00"));
                                vendingMachine.addFedMoneyToLog(new BigDecimal("10.00"));
                            }
                        }
                    if (subMenuChoice.equals(SELECT_PRODUCT)) {

                        Map<String, VendingMachineItem> inventoryList = vendingMachine.getInventory();

                        System.out.println("\n**** MENU OPTIONS ****\n");
                        for (Map.Entry<String, VendingMachineItem> entry : inventoryList.entrySet()) {
                            System.out.println("Item Code: " + entry.getKey() + " " + entry.getValue());
                        }

                        System.out.println("\nPlease enter the code for the item you'd like to purchase: ");

                        String itemChoice = menu.getProductChoice(vendingMachineItem);
                        VendingMachineItem customerChoice = inventoryList.get(itemChoice);

                        if (inventoryList.containsKey(itemChoice)) {
                            if (vendingMachineItem.getQuantity() > 0) {
                                BigDecimal productPrice = customerChoice.getProductPriceBD();
                                if (productPrice.compareTo(vendingMachine.getMoneyProvided()) <= 0) {

                                    System.out.println(inventoryList.get(itemChoice) + " Remaining Money: " + vendingMachine.getMoneyProvided().subtract(productPrice));
                                    vendingMachineItem.updateProductQuantity(1);

                                    if (customerChoice.getProductType().equals("Chip")) {
                                        System.out.println("Crunch Crunch, Yum!");
                                        vendingMachine.subtractFromMoneyProvided(new BigDecimal(customerChoice.getProductPrice()));
                                        vendingMachine.addPurchaseToLog(customerChoice);
                                        System.out.println("Number of " + customerChoice.getProductName() + " left: " + vendingMachineItem.getQuantity());
                                    }
                                    if (customerChoice.getProductType().equals("Candy")) {
                                        System.out.println("Munch Munch, Yum!");
                                        vendingMachine.subtractFromMoneyProvided(new BigDecimal(customerChoice.getProductPrice()));
                                        vendingMachine.addPurchaseToLog(customerChoice);
                                        System.out.println("Number of " + customerChoice.getProductName() + " left: " + vendingMachineItem.getQuantity());
                                    }
                                    if (customerChoice.getProductType().equals("Drink")) {
                                        System.out.println("Glug Glug, Yum!");
                                        vendingMachine.subtractFromMoneyProvided(new BigDecimal(customerChoice.getProductPrice()));
                                        vendingMachine.addPurchaseToLog(customerChoice);
                                        System.out.println("Number of " + customerChoice.getProductName() + " left: " + vendingMachineItem.getQuantity());
                                    }
                                    if (customerChoice.getProductType().equals("Gum")) {
                                        System.out.println("Chew Chew, Yum!");
                                        vendingMachine.subtractFromMoneyProvided(new BigDecimal(customerChoice.getProductPrice()));
                                        vendingMachine.addPurchaseToLog(customerChoice);
                                        System.out.println("Number of " + customerChoice.getProductName() + " left: " + vendingMachineItem.getQuantity());
                                    }
                                } else {
                                    System.out.println("Insufficient funds! Please add more money.");
                                }
                            } else {
                                System.out.println("That product is out of stock! Please select a different product.");
                            }
                        } else {
                            System.out.println("That product code does not exist! Please try again.");
                        }
                    }
                    if (subMenuChoice.equals(FINISH_TRANSACTION)) {
                        BigDecimal change = vendingMachine.getMoneyProvided();
                        vendingMachine.addChangeReturnedToLog(change);

                        change = change.multiply(new BigDecimal("100"));
                        int quarters = change.intValue() / 25;
                        change = change.remainder(new BigDecimal("25.00"));
                        int dimes = change.intValue() / 10;
                        change = change.remainder(new BigDecimal("10.0"));
                        int nickels = change.intValue() / 5;
                        change = change.remainder(new BigDecimal("5.00"));


                        System.out.println("Here is your change! " + quarters + " Quarters, " + dimes + " Dimes, and " + nickels + " nickels.");
                        System.out.println("\nCash Available To Spend: $" + change);
                        subRunning = false;
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                running = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
