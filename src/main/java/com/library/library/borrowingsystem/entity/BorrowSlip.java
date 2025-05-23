package com.library.library.borrowingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_slips")
@Getter
@Setter
public class BorrowSlip {
    @Id
    @Column(name = "slip_id")
    private String id;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "books")
    private String books;

    @Column(name = "borrow_date")
    private String borrowDate;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 