package com.company.HyunseokKahngU1Capstone.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {

    private int gameId;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String title;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String ersbRating;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String description;

    @NotNull(message = "Field cannot be null.")
    private BigDecimal price;

    @NotNull(message = "Field cannot be null.")
    @NotEmpty(message = "Please do not enter an empty string")
    private String studio;

    @NotNull(message = "Field cannot be null.")
    @Min(value = 0L, message = "You must enter a quantity that is zero or more.")
    private Integer quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getErsbRating() {
        return ersbRating;
    }

    public void setErsbRating(String ersbRating) {
        this.ersbRating = ersbRating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        GameViewModel game = (GameViewModel) o;
        return getGameId() == game.getGameId() &&
                Objects.equals(getTitle(), game.getTitle()) &&
                Objects.equals(getErsbRating(), game.getErsbRating()) &&
                Objects.equals(getDescription(), game.getDescription()) &&
                Objects.equals(getPrice(), game.getPrice()) &&
                Objects.equals(getStudio(), game.getStudio()) &&
                Objects.equals(getQuantity(), game.getQuantity());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getGameId(),getTitle(),getErsbRating(),getDescription(),getPrice(),getStudio(),getQuantity());
    }
}
