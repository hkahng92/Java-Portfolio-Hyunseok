package com.company.retailapi.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    //properties according to the Schema
    private int productId;
    private String productName;
    private String productDescription;
    private BigDecimal listPrice;
    private BigDecimal unitCost;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productDescription, product.productDescription) &&
                Objects.equals(listPrice, product.listPrice) &&
                Objects.equals(unitCost, product.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, listPrice, unitCost);
    }

    @Override
    public String toString(){
        return "Product{" +
                "productId=" + productId +
                ", productName=" + productName +
                ", productDescription='" + productDescription +
                ", listPrice='" + listPrice +
                ", unitCost='" + unitCost +
                '\'' +
                '}';
    }
}
