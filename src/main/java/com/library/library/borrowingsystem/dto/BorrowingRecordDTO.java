package com.library.library.borrowingsystem.dto;

import com.library.library.borrowingsystem.entity.BorrowingRecord;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRecordDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Long patronId;
    private String patronName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private BorrowingRecord.BorrowingStatus status;
}