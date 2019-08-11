package com.company.HyunseokKahngU1Capstone.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {

    private int consoleId;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String model;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String manufacturer;

    private String memoryAmount;
    private String processor;

    @NotNull(message = "Field cannot be null.")
    private BigDecimal price;

    @NotNull(message = "Field cannot be null.")
    @Min(value = 0L, message = "You must enter a quantity that is zero or more.")
    private Integer Quantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleViewModel console = (ConsoleViewModel) o;
        return getConsoleId() == console.getConsoleId() &&
                Objects.equals(getModel(), console.getModel()) &&
                Objects.equals(getManufacturer(), console.getManufacturer()) &&
                Objects.equals(getMemoryAmount(), console.getMemoryAmount()) &&
                Objects.equals(getProcessor(), console.getProcessor()) &&
                Objects.equals(getPrice(), console.getPrice()) &&
                Objects.equals(getQuantity(), console.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getProcessor(), getQuantity());
    }
}
