package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.dto.BorrowingRecordDTO;
import com.library.library.borrowingsystem.entity.BorrowingRecord;
import com.library.library.borrowingsystem.service.BorrowingService;
import com.library.library.borrowingsystem.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;
    private final MapperService mapperService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingRecordDTO> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long patronId) {
        BorrowingRecord record = borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTO(record));
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Long id) {
        BorrowingRecord record = borrowingService.returnBook(id);
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTO(record));
    }

    @GetMapping
    public ResponseEntity<List<BorrowingRecordDTO>> getAllBorrowingRecords() {
        List<BorrowingRecord> records = borrowingService.getAllBorrowingRecords();
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTOs(records));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowingRecordDTO>> getOverdueRecords() {
        List<BorrowingRecord> records = borrowingService.getOverdueRecords();
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTOs(records));
    }

    @GetMapping("/patron/{patronId}")
    public ResponseEntity<List<BorrowingRecordDTO>> getBorrowingsByPatron(@PathVariable Long patronId) {
        List<BorrowingRecord> records = borrowingService.getBorrowingsByPatron(patronId);
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTOs(records));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowingRecordDTO>> getBorrowingsByBook(@PathVariable Long bookId) {
        List<BorrowingRecord> records = borrowingService.getBorrowingsByBook(bookId);
        return ResponseEntity.ok(mapperService.toBorrowingRecordDTOs(records));
    }
}