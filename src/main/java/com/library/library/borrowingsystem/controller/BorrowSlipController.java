package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.entity.BorrowSlip;
import com.library.library.borrowingsystem.service.BorrowSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow-slips")
public class BorrowSlipController {

    @Autowired
    private BorrowSlipService borrowSlipService;

    @GetMapping
    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipService.getAllBorrowSlips();
    }

    @GetMapping("/active/{studentId}")
    public List<BorrowSlip> getActiveBorrowSlips(@PathVariable String studentId) {
        return borrowSlipService.getActiveBorrowSlipsByStudentId(studentId);
    }

    @PostMapping
    public ResponseEntity<BorrowSlip> createBorrowSlip(@RequestBody BorrowSlip borrowSlip) {
        BorrowSlip savedSlip = borrowSlipService.createBorrowSlip(borrowSlip);
        return ResponseEntity.ok(savedSlip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowSlip> updateBorrowSlip(
            @PathVariable String id,
            @RequestBody BorrowSlip borrowSlip
    ) {
        BorrowSlip updatedSlip = borrowSlipService.updateBorrowSlip(id, borrowSlip);
        return ResponseEntity.ok(updatedSlip);
    }
} 