package com.jackgroom.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Book {
    public final String name;
    public final String author;
    public final BookGenre genre;
    public final LocalDate releaseDate;

    private int quantity;
    private BigDecimal price;

    public Book(String name, String author, BookGenre genre, LocalDate releaseDate, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.price = price;
        this.quantity = 1;
        System.out.println("Hello");
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
        if (quantity < 0) { quantity = 0; }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", releaseDate=" + releaseDate +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
