package com.company.U1M6Summative.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    private Integer invoiceItemId;
    private Integer invoiceId;
    private Integer itemId;
    private Integer quantity;
    private BigDecimal unitRate;
    private BigDecimal discount;

    public Integer getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Integer invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceItem invoiceItem = (InvoiceItem) o;
        return getInvoiceItemId() == invoiceItem.getInvoiceItemId()&&
               getInvoiceId() == invoiceItem.getInvoiceId() &&
                getItemId() == invoiceItem.getItemId() &&
                Objects.equals(getQuantity(), invoiceItem.getQuantity()) &&
                Objects.equals(getUnitRate(), invoiceItem.getUnitRate()) &&
                Objects.equals(getDiscount(), invoiceItem.getDiscount())
                ;
    }

    @Override
    public int hashCode(){
        return Objects.hash(
                getInvoiceItemId(),
                getInvoiceId(),
                getItemId(),
                getQuantity(),
                getUnitRate(),
                getDiscount()
        );
    }
}
