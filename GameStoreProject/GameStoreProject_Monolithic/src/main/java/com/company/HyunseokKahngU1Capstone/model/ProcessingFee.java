package com.company.HyunseokKahngU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProcessingFee {

    private String productType;
    //this could be null
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee processingFee = (ProcessingFee) o;
        return Objects.equals(getProductType(), processingFee.getProductType()) &&
                Objects.equals(getFee(),processingFee.getFee());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getProductType(),getFee());
    }
}
