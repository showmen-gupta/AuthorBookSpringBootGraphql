package com.app.authortutorialspringboot.controller;

import com.app.authortutorialspringboot.entity.Author;
import com.app.authortutorialspringboot.entity.Book;
import com.app.authortutorialspringboot.repository.AuthorRepository;
import com.app.authortutorialspringboot.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    BookController bookController;

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        authorRepository = mock(AuthorRepository.class);
        bookController = new BookController(bookRepository, authorRepository);
    }

    @Test
    @DisplayName("It should add books with author")
    public void testAddBooks() {
        when(bookRepository.save(any())).thenReturn(populateBook());
        when(authorRepository.findById(any())).thenReturn(Optional.of(populateAuthor("Pep")));
        var book = bookController.addBook("Test Book 1", 200.0, 1L);
        assertEquals("Test Book 1", book.getName());
        assertEquals(200.0, book.getPrice());
        assertEquals("Pep", book.getAuthor().getName());
    }

    @Test
    @DisplayName("It should return all books")
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(populateBookList());
        var allBooks = bookController.allBooks().iterator();
        var bookList = new ArrayList<>();
        while (allBooks.hasNext()) {
            bookList.add(allBooks.next());
        }
        assertEquals(2, bookList.size());
    }

    @Test
    @DisplayName("It should return single book")
    public void testGetSingleBook() {
        when(bookRepository.findById(any())).thenReturn(Optional.of(populateBook()));
        var book = bookController.book(1L);
        assertEquals("Test Book 1", book.getName());
        assertEquals(200.0, book.getPrice());
        assertEquals("Pep", book.getAuthor().getName());
    }

    @Test
    @DisplayName("It should update a book")
    public void testUpdateBook() {
        when(bookRepository.save(any())).thenReturn(populateBook());
        var book = bookController.updateBook(1L, "Test Book 1", 200.0);
        assertEquals("Test Book 1", book.getName());
        assertEquals(200.0, book.getPrice());
        assertEquals("Pep", book.getAuthor().getName());
    }

    @Test
    @DisplayName("It should delete a book")
    public void testDeleteBook() {
        when(bookRepository.save(any())).thenReturn(populateBook());
        var IsDeleted = bookController.deleteBook(1L);
        assertTrue(IsDeleted);
    }

    private Book populateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("Test Book 1");
        book.setPrice(200.0);
        book.setAuthor(populateAuthor("Pep"));
        return book;
    }

    private List<Book> populateBookList() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Harry Potter 1");
        book1.setPrice(500.0);
        book1.setAuthor(populateAuthor("Roman"));
        bookList.add(book1);

        Book book2 = new Book();
        book2.setId(1L);
        book2.setName("Harry Potter 1");
        book2.setPrice(1500.0);
        book2.setAuthor(populateAuthor("Shiv"));
        bookList.add(book2);
        return bookList;
    }

    private Author populateAuthor(String authorName) {
        Author author = new Author();
        author.setId(1L);
        author.setName(authorName);
        return author;
    }
}
