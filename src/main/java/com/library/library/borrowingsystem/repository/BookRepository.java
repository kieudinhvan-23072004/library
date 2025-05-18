package com.library.library.borrowingsystem.repository;

import com.library.library.borrowingsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
    Book findByIsbn(String isbn);
}