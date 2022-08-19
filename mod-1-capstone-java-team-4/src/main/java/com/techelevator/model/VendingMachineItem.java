package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class VendingMachineItem {

    private String productName;
    private String productPrice;
    private String productCode;
    private String productType;
    private int quantity;

    public VendingMachineItem(String productCode, String productName, String productPrice, String productType, int quantity) {
        this.productType = productType;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public VendingMachineItem() {
    }

    public VendingMachineItem(String productCode) {
        this.productCode = productCode;
    }

    public VendingMachineItem(int quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public BigDecimal getProductPriceBD() {
        return new BigDecimal(getProductPrice());
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int updateProductQuantity(int subtractFromStock) {
        quantity = quantity - subtractFromStock;
        return quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineItem that = (VendingMachineItem) o;
        return quantity == that.quantity && Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice) && Objects.equals(productCode, that.productCode) && Objects.equals(productType, that.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, productCode, quantity);
    }

    @Override
    public String toString() {
        return "Item: " + productName + " Price: $" + productPrice;
    }
}
