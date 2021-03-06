package com.company.HyunseokKahngU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

    private int invoiceId;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String name;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String street;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String city;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    @Size(min =2, max = 2, message = "You must input state as an abbreviation. e.g. NJ")
    private String state;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    @Size(min = 5, max = 5, message = "Your Zipcode must be in 5 digits")
    private String zipcode;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String itemType;

    @NotNull(message = "Field cannot be null.")
    @Min(value = 1L, message = "You must enter a valid Item Id.")
    private Integer itemId;

    @NotNull(message = "Field cannot be null.")
    @Min(value = 0L, message = "You must enter a quantity that is zero or more.")
    private Integer quantity;

    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel invoice = (InvoiceViewModel) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                Objects.equals(getName(),invoice.getName()) &&
                Objects.equals(getStreet(),invoice.getStreet()) &&
                Objects.equals(getCity(),invoice.getCity()) &&
                Objects.equals(getState(),invoice.getState()) &&
                Objects.equals(getZipcode(),invoice.getZipcode()) &&
                Objects.equals(getItemType(),invoice.getItemType()) &&
                Objects.equals(getItemId(),invoice.getItemId()) &&
                Objects.equals(getUnitPrice(),invoice.getUnitPrice()) &&
                Objects.equals(getQuantity(),invoice.getQuantity()) &&
                Objects.equals(getSubtotal(),invoice.getSubtotal()) &&
                Objects.equals(getTax(),invoice.getTax()) &&
                Objects.equals(getProcessingFee(),invoice.getProcessingFee()) &&
                Objects.equals(getTotal(),invoice.getTotal());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getInvoiceId(),
                getName(),
                getStreet(),
                getCity(),
                getState(),
                getZipcode(),
                getItemType(),
                getItemId(),
                getUnitPrice(),
                getQuantity(),
                getSubtotal(),
                getTax(),
                getProcessingFee(),
                getTotal()
        );
    }
}
