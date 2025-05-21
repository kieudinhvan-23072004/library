package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.entity.BorrowingRecord;
import com.library.library.borrowingsystem.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor

public class BorrowingController {
    private final BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecord> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long patronId) {
        return ResponseEntity.ok(borrowingService.borrowBook(bookId, patronId));
    }

    @PostMapping("/return/{recordId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long recordId) {
        return ResponseEntity.ok(borrowingService.returnBook(recordId));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowingRecord>> getOverdueRecords() {
        return ResponseEntity.ok(borrowingService.getOverdueRecords());
    }
}