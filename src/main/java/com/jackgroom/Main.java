package com.jackgroom;

import com.jackgroom.book.BookGenre;
import com.jackgroom.book.BookService;
import com.jackgroom.db.TemporaryDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BookService.addNewBook("TestBook", "Jack Groom", BookGenre.HORROR, LocalDate.now(), BigDecimal.valueOf(100));
        BookService.addNewBook("Harry Potter", "J.K.Rowling", BookGenre.FANTASY, LocalDate.of(1995, 6, 14), BigDecimal.valueOf(9.99));
        BookService.addNewBook("Lord of the Rings", "J.R.R.Tolkien", BookGenre.FANTASY, LocalDate.of(1934, 10, 29), BigDecimal.valueOf(15));
        BookService.addNewBook("Fifty Shades of Grey", "Some Loser", BookGenre.ROMANCE, LocalDate.of(2010, 12, 16), BigDecimal.valueOf(12.99));
        BookService.addNewBook("Corpse Party", "Some Japanese Guy", BookGenre.HORROR, LocalDate.of(2014, 10, 2), BigDecimal.valueOf(11.37));
        BookService.addNewBook("Star Wars", "George Lucas", BookGenre.SCIFI, LocalDate.of(1960, 4, 4), BigDecimal.valueOf(0.3));
        BookService.addNewBook("Skullduggery Pleasant", "Cant remember", BookGenre.MYSTERY, LocalDate.of(2014, 6, 11), BigDecimal.valueOf(15.37));
        BookService.addNewBook("Dune", "Who knows? (not me)", BookGenre.SCIFI, LocalDate.of(2100, 3, 14), BigDecimal.valueOf(27.62));
        BookService.addNewBook("Cherub", "Not a clue", BookGenre.THRILLER, LocalDate.of(2013, 12, 12), BigDecimal.valueOf(20));

        TemporaryDatabase.getBooks().forEach(System.out::println);
    }
}
