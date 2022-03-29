package com.jackgroom.db;

import com.jackgroom.book.Book;

import java.util.ArrayList;
import java.util.List;

public class TemporaryDatabase {
    private static final List<Book> Books = new ArrayList<>();

    public static List<Book> getBooks() {
        return Books;
    }

    public static void addBook(Book newBook) {
        Books.add(newBook);
    }

    public static void removeAll() {
        Books.clear();
    }
}
