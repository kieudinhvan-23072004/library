package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.dto.BorrowingRecordDTO;
import com.library.library.borrowingsystem.entity.BorrowingRecord;
import com.library.library.borrowingsystem.service.BorrowingService;
import com.library.library.borrowingsystem.service.MapperService;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity