package com.app.authortutorialspringboot.controller;

import com.app.authortutorialspringboot.entity.Author;
import com.app.authortutorialspringboot.entity.Book;
import com.app.authortutorialspringboot.repository.AuthorRepository;
import com.app.authortutorialspringboot.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book book(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addBook(@Argument String name, @Argument Double price, @Argument Long author_id) {
        Author author = authorRepository.findById(author_id).orElse(null);
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument String name, @Argument Double price) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setPrice(price);

        return bookRepository.save(book);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        bookRepository.deleteById(id);
        return true;
    }
}
