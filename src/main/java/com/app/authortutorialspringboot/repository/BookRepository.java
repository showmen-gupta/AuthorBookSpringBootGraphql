package com.app.authortutorialspringboot.repository;

import com.app.authortutorialspringboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
