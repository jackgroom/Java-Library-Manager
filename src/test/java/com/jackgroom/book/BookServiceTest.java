package com.jackgroom.book;

import com.jackgroom.db.TemporaryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @BeforeEach
    public void setUp() {
        TemporaryDatabase.removeAll();

        // a bunch of books with real names but random prices, authors and release dates (didn't know them off the top of my head)
        BookService.addNewBook("TestBook", "Jack Groom", BookGenre.HORROR, LocalDate.now(), BigDecimal.valueOf(100));
        BookService.addNewBook("TestBook The Sequel", "Jack Groom", BookGenre.MYSTERY, LocalDate.now(), BigDecimal.valueOf(100));
        BookService.addNewBook("Harry Potter", "J.K.Rowling", BookGenre.FANTASY, LocalDate.of(1995, 6, 14), BigDecimal.valueOf(9.99));
        BookService.addNewBook("Lord of the Rings", "Tolkien", BookGenre.FANTASY, LocalDate.of(1934, 10, 29), BigDecimal.valueOf(15));
        BookService.addNewBook("Fifty Shades of Grey", "Some dude", BookGenre.ROMANCE, LocalDate.of(2010, 12, 16), BigDecimal.valueOf(12.99));
        BookService.addNewBook("Corpse Party", "Some Japanese Guy", BookGenre.HORROR, LocalDate.of(2014, 10, 2), BigDecimal.valueOf(11.37));
        BookService.addNewBook("Star Wars", "George Lucas", BookGenre.SCIFI, LocalDate.of(1960, 4, 4), BigDecimal.valueOf(0.3));
        BookService.addNewBook("Skullduggery Pleasant", "Cant remember", BookGenre.MYSTERY, LocalDate.of(2014, 6, 11), BigDecimal.valueOf(15.37));
        BookService.addNewBook("Dune", "Who knows? (not me)", BookGenre.SCIFI, LocalDate.of(2100, 3, 14), BigDecimal.valueOf(27.62));
        BookService.addNewBook("Cherub", "Not a clue", BookGenre.THRILLER, LocalDate.of(2013, 12, 12), BigDecimal.valueOf(20));
    }

    @Test
    void getBookByName() {
        assertNotNull(BookService.getBookByName("Harry Potter"));
        assertNull(BookService.getBookByName("Foo"));
    }

    @Test
    void getBooksByAuthor() {
        assertEquals(1, BookService.getBooksByAuthor("J.K.Rowling").size());
        assertEquals(2, BookService.getBooksByAuthor("Jack Groom").size());
        assertEquals(0, BookService.getBooksByAuthor("Foo").size());
    }

    @Test
    void getBooksByGenre() {
        assertEquals(2, BookService.getBooksByGenre(BookGenre.FANTASY).size());
        assertEquals(2, BookService.getBooksByGenre(BookGenre.HORROR).size());
        assertEquals(0, BookService.getBooksByGenre(BookGenre.EDUCATION).size());
    }

    @Test
    void getBooksByPriceRange() {
        assertEquals(10, BookService.getBooksByPriceRange(BigDecimal.valueOf(9999)).size());
        assertEquals(1, BookService.getBooksByPriceRange(BigDecimal.valueOf(1)).size());
        assertEquals(2, BookService.getBooksByPriceRange(BigDecimal.valueOf(10)).size());
        assertEquals(5, BookService.getBooksByPriceRange(BigDecimal.valueOf(15)).size());
        assertEquals(0, BookService.getBooksByPriceRange(BigDecimal.valueOf(0)).size());
    }

    @Test
    void updateBookPriceByName() {
        BookService.updateBookPriceByName("Harry Potter", BigDecimal.valueOf(30));
        assertNotEquals(BigDecimal.valueOf(9.99), BookService.getBookByName("Harry Potter").getPrice());
        assertEquals(BigDecimal.valueOf(30), BookService.getBookByName("Harry Potter").getPrice());

        BookService.updateBookPriceByName("Hary Poter", BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(30), BookService.getBookByName("Harry Potter").getPrice());
    }

    @Test
    void incrementBookQuantityByName() {
        BookService.incrementBookQuantityByName("Harry Potter");
        assertEquals(2, BookService.getBookByName("Harry Potter").getQuantity());

        BookService.incrementBookQuantityByName("Cherub");
        BookService.incrementBookQuantityByName("Cherub");
        assertEquals(3, BookService.getBookByName("Cherub").getQuantity());
    }

    @Test
    void decrementBookQuantityByName() {
        BookService.decrementBookQuantityByName("Harry Potter");
        assertEquals(0, BookService.getBookByName("Harry Potter").getQuantity());

        BookService.decrementBookQuantityByName("Cherub");
        BookService.decrementBookQuantityByName("Cherub");
        BookService.decrementBookQuantityByName("Cherub");
        BookService.decrementBookQuantityByName("Cherub");
        assertEquals(0, BookService.getBookByName("Cherub").getQuantity());
    }
}
