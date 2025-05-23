package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.entity.BorrowSlip;
import com.library.library.borrowingsystem.repository.BorrowSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BorrowSlipService {

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    public List<BorrowSlip> getAllBorrowSlips() {
        return borrowSlipRepository.findAll();
    }

    public List<BorrowSlip> getActiveBorrowSlipsByStudentId(String studentId) {
        return borrowSlipRepository.findByStudentIdAndStatus(studentId, "Active");
    }

    public BorrowSlip createBorrowSlip(BorrowSlip borrowSlip) {
        borrowSlip.setId(UUID.randomUUID().toString());
        borrowSlip.setCreatedAt(LocalDateTime.now());
        borrowSlip.setUpdatedAt(LocalDateTime.now());
        return borrowSlipRepository.save(borrowSlip);
    }

    public BorrowSlip updateBorrowSlip(String id, BorrowSlip borrowSlip) {
        BorrowSlip existingSlip = borrowSlipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow slip not found with id: " + id));

        existingSlip.setBorrowerName(borrowSlip.getBorrowerName());
        existingSlip.setStudentId(borrowSlip.getStudentId());
        existingSlip.setContactEmail(borrowSlip.getContactEmail());
        existingSlip.setContactPhone(borrowSlip.getContactPhone());
        existingSlip.setBooks(borrowSlip.getBooks());
        existingSlip.setBorrowDate(borrowSlip.getBorrowDate());
        existingSlip.setDueDate(borrowSlip.getDueDate());
        existingSlip.setStatus(borrowSlip.getStatus());
        existingSlip.setUpdatedAt(LocalDateTime.now());

        return borrowSlipRepository.save(existingSlip);
    }
} 