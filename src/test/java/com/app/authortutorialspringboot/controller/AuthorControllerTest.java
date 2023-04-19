package com.app.authortutorialspringboot.controller;

import com.app.authortutorialspringboot.entity.Author;
import com.app.authortutorialspringboot.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorControllerTest {
    @Autowired
    private AuthorRepository authorRepository;
    AuthorController authorController;

    @BeforeEach
    public void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorController = new AuthorController(authorRepository);
    }

    @Test
    @DisplayName("It should add authors")
    public void testAddAuthors() {
        when(authorRepository.save(any())).thenReturn(populateAuthor("Pep"));
        var author = authorController.addAuthor(any());
        assertEquals("Pep", author.getName());
    }

    @Test
    @DisplayName("It should return all authors")
    public void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(populateAuthorList());
        var allAuthors = authorController.allAuthors().iterator();
        var authorList = new ArrayList<>();
        while (allAuthors.hasNext()) {
            authorList.add(allAuthors.next());
        }
        assertEquals(2, authorList.size());
    }

    @Test
    @DisplayName("It should return one author")
    public void testGetSingleAuthor() {
        when(authorRepository.findById(any())).thenReturn(Optional.of(populateAuthor("Pep")));
        var author = authorController.author(1L);
        assertEquals("Pep", author.getName());
    }

    @Test
    @DisplayName("It should update a author")
    public void testUpdateAuthor() {
        when(authorRepository.save(any())).thenReturn(populateAuthor("Pope"));
        var author = authorController.updateAuthor(1L, any());
        assertEquals("Pope", author.getName());
    }

    @Test
    @DisplayName("It should update a author")
    public void testDeleteAuthor() {
        when(authorRepository.save(any())).thenReturn(populateAuthor("Pep"));
        var IsDeleted = authorController.deleteAuthor(1L);
        assertTrue(IsDeleted);
    }

    private Author populateAuthor(String name) {
        Author author = new Author();
        author.setId(1L);
        author.setName(name);
        return author;
    }

    private List<Author> populateAuthorList() {
        List<Author> authorList = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("Showmen");
        authorList.add(author1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Luca");
        authorList.add(author2);

        return authorList;
    }
}
