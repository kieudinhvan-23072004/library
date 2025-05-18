package com.library.library.borrowingsystem.repository;

import com.library.library.borrowingsystem.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    boolean existsByEmail(String email);
}