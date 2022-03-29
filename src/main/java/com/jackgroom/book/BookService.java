package com.jackgroom.book;

import com.jackgroom.db.TemporaryDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    public static void addNewBook(
            String name,
            String author,
            BookGenre genre,
            LocalDate releaseDate,
            BigDecimal price
    ) {
        Optional.ofNullable(getBookByName(name))
                .map(book -> {
                    book.incrementQuantity();
                    return book;
                }).orElseGet(() -> {
                    Book newBook = new Book(name, author, genre, releaseDate, price);
                    TemporaryDatabase.addBook(newBook);
                    return newBook;
                });
    }

    public static Book getBookByName(String name) {
        List<Book> books = TemporaryDatabase.getBooks();
        return books.stream()
                .filter(book -> book.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Book> getBooksByAuthor(String author) {
        List<Book> books = TemporaryDatabase.getBooks();
        return books.stream()
                .filter(book -> book.author.equals(author))
                .collect(Collectors.toList());
    }

    public static List<Book> getBooksByGenre(BookGenre genre) {
        List<Book> books = TemporaryDatabase.getBooks();
        return books.stream()
                .filter(book -> book.genre.equals(genre))
                .collect(Collectors.toList());
    }

    public static List<Book> getBooksByPriceRange(BigDecimal max) {
        List<Book> books = TemporaryDatabase.getBooks();
        return books.stream()
                .filter(book -> book.getPrice() != null && book.getPrice().compareTo(max) <= 0)
                .sorted(Comparator.comparing(Book::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public static void updateBookPriceByName(String name, BigDecimal newPrice) {
        Optional.ofNullable(getBookByName(name))
                .ifPresent(book -> book.setPrice(newPrice));
    }

    public static void incrementBookQuantityByName(String name) {
        Optional.ofNullable(getBookByName(name))
                .ifPresent(Book::incrementQuantity);
    }

    public static void decrementBookQuantityByName(String name) {
        Optional.ofNullable(getBookByName(name))
                .ifPresent(Book::decrementQuantity);
    }
}
