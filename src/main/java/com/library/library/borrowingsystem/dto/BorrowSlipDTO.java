package com.library.library.borrowingsystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BorrowSlipDTO {
    private String id;
    private String borrowerName;
    private String studentId;
    private String contactEmail;
    private String contactPhone;
    private String books;
    private String borrowDate;
    private String dueDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 