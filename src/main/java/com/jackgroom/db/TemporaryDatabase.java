package com.jackgroom.db;

import com.jackgroom.book.Book;
import com.jackgroom.book.BookGenre;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TemporaryDatabase {
    private static final List<Book> Books = new ArrayList<>();

    public static List<Book> getBooks() {
        return Books;
    }

    public static Book addBook(
            String name,
            String author,
            BookGenre genre,
            LocalDate releaseDate,
            BigDecimal price
    ) {
        Book newBook = new Book(name, author, genre, releaseDate, price);
        Books.add(newBook);

        return newBook;
    }

    public static void removeAll() {
        Books.clear();
    }
}
