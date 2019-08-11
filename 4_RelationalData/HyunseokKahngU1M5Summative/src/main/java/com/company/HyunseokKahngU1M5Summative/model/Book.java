package com.company.HyunseokKahngU1M5Summative.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Book {

    private int id;
    private String isbn;
    private LocalDate publishDate;
    private int authorId;
    private String title;
    private int publisherId;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return  Objects.equals(getId() , book.getId()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getPublishDate(), book.getPublishDate()) &&
                Objects.equals(getAuthorId(), book.getAuthorId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublisherId(), book.getPublisherId()) &&
                Objects.equals(getPrice(), book.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getIsbn(),
                getPublishDate(),
                getAuthorId(),
                getTitle(),
                getPublisherId(),
                getPrice()
        );
    }






}
