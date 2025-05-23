package com.library.library.borrowingsystem.repository;

import com.library.library.borrowingsystem.entity.BorrowSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowSlipRepository extends JpaRepository<BorrowSlip, String> {
    List<BorrowSlip> findByStudentIdAndStatus(String studentId, String status);
} 