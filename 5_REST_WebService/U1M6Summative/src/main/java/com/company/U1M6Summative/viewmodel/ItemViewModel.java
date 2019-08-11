package com.company.U1M6Summative.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemViewModel {
    private Integer itemId;
    @NotNull(message = "Field cannot be empty.")
    private String name;
    @NotNull(message = "Field cannot be empty.")
    private String description;
    @NotNull(message = "Field cannot be empty.")
    private BigDecimal dailyRate;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ItemViewModel that = (ItemViewModel) o;
        return getItemId() == that.getItemId() &&
                Objects.equals(getName(),that.getName()) &&
                Objects.equals(getDescription(),that.getDescription())&&
                Objects.equals(getDailyRate(),that.getDailyRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(),getName(),getDescription(),getDailyRate());
    }
}
