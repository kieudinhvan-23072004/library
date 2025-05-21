package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.entity.*;
import com.library.library.borrowingsystem.exception.*;
import com.library.library.borrowingsystem.repository.BookRepository;
import com.library.library.borrowingsystem.repository.BorrowingRecordRepository;
import com.library.library.borrowingsystem.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Value("${app.max-borrow-days:14}")
    private int maxBorrowDays;

    @Value("${app.max-books-per-user:5}")
    private int maxBooksPerUser;

    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron", "id", patronId));

        if (book.getAvailableQuantity() <= 0) {
            throw new BookNotAvailableException("Book is not available for borrowing");
        }

        long activeBorrowings = borrowingRecordRepository.countByPatronIdAndStatus(patronId,
                BorrowingRecord.BorrowingStatus.BORROWED);

        if (activeBorrowings >= maxBooksPerUser) {
            throw new BorrowingLimitExceededException("Patron has reached maximum borrowing limit");
        }

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(maxBorrowDays));
        record.setStatus(BorrowingRecord.BorrowingStatus.BORROWED);

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        return borrowingRecordRepository.save(record);
    }

    @Transactional
    public BorrowingRecord returnBook(Long recordId) {
        BorrowingRecord record = borrowingRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found with id: " + recordId));

        if (record.getStatus() != BorrowingRecord.BorrowingStatus.BORROWED) {
            throw new InvalidOperationException("Book is not currently borrowed");
        }

        Book book = record.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);

        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowingRecord.BorrowingStatus.RETURNED);

        return borrowingRecordRepository.save(record);
    }

    public List<BorrowingRecord> getOverdueRecords() {
        return borrowingRecordRepository.findOverdueRecords(LocalDate.now());
    }

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    // Phương thức mới thêm vào để lấy danh sách mượn sách theo người dùng
    public List<BorrowingRecord> getBorrowingsByPatron(Long patronId) {
        if (!patronRepository.existsById(patronId)) {
            throw new ResourceNotFoundException("Patron", "id", patronId);
        }
        return borrowingRecordRepository.findByPatronId(patronId);
    }

    // Phương thức mới thêm vào để lấy danh sách mượn sách theo sách
    public List<BorrowingRecord> getBorrowingsByBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book", "id", bookId);
        }
        return borrowingRecordRepository.findByBookId(bookId);
    }
}