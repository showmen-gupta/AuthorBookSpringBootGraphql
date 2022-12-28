package com.app.authortutorialspringboot.controller;

import com.app.authortutorialspringboot.entity.Author;
import com.app.authortutorialspringboot.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    Iterable<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Author author(@Argument Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    Author addAuthor(@Argument String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }

    @MutationMapping
    Author updateAuthor(@Argument Long id, @Argument String name) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        return authorRepository.save(author);
    }

    @MutationMapping
    Boolean deleteAuthor(@Argument Long id) {
        authorRepository.deleteById(id);
        return true;
    }
}
