package com.library.library.borrowingsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
@Getter
@Setter
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @Column(nullable = false)
    private LocalDate borrowDate;

    private LocalDate returnDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

    public enum BorrowingStatus {
        BORROWED, RETURNED, OVERDUE
    }
}