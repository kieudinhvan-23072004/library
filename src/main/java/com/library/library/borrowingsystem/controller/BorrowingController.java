package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.dto.BorrowRequestDTO;
import com.library.library.borrowingsystem.entity.BorrowSlip;
import com.library.library.borrowingsystem.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowSlip> createBorrowSlip(@RequestBody BorrowRequestDTO request) {
        try {
            BorrowSlip savedSlip = borrowingService.createBorrowSlipWithBooks(
                request.getBorrowSlip(), 
                request.getBookTitles()
            );
            return ResponseEntity.ok(savedSlip);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating borrow slip: " + e.getMessage());
        }
    }

    @PutMapping("/update-quantities")
    public ResponseEntity<Void> updateBookQuantities(@RequestBody List<String> bookIds) {
        try {
            borrowingService.updateBookQuantities(bookIds);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating book quantities: " + e.getMessage());
        }
    }
}