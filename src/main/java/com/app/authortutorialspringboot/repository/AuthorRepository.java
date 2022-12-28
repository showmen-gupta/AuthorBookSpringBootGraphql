package com.app.authortutorialspringboot.repository;

import com.app.authortutorialspringboot.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
