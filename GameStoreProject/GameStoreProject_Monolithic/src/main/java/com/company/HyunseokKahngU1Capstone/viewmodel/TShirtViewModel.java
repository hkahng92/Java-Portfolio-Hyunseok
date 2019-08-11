package com.company.HyunseokKahngU1Capstone.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirtViewModel {

    private int tShirtId;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String size;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String color;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String description;

    @NotNull(message = "Field cannot be null.")
    private BigDecimal price;

    @NotNull(message = "Field cannot be null.")
    @Min(value = 0L, message = "You must enter a quantity that is zero or more.")
    private Integer quantity;

    public int gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel tShirt = (TShirtViewModel) o;
        return gettShirtId() == tShirt.gettShirtId() &&
                Objects.equals(getSize(), tShirt.getSize()) &&
                Objects.equals(getColor(), tShirt.getColor()) &&
                Objects.equals(getDescription(), tShirt.getDescription()) &&
                Objects.equals(getPrice(), tShirt.getPrice()) &&
                Objects.equals(getQuantity(), tShirt.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gettShirtId(), getSize(),getColor(),getDescription(),getPrice(),getQuantity());
    }
}
