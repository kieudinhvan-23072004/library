package com.library.library.borrowingsystem.repository;

import com.library.library.borrowingsystem.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByPatronId(Long patronId);
    List<BorrowingRecord> findByBookId(Long bookId);
    long countByPatronIdAndStatus(Long patronId, BorrowingRecord.BorrowingStatus status);
    @Query("SELECT br FROM BorrowingRecord br WHERE br.dueDate < :currentDate AND br.status = 'BORROWED'")
    List<BorrowingRecord> findOverdueRecords(@Param("currentDate") LocalDate currentDate);
}