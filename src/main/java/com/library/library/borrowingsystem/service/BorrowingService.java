package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.entity.BorrowSlip;
import com.library.library.borrowingsystem.exception.LibraryException;
import com.library.library.borrowingsystem.repository.BookRepository;
import com.library.library.borrowingsystem.repository.BorrowSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowSlipRepository borrowSlipRepository;

    @Transactional
    public BorrowSlip createBorrowSlipWithBooks(BorrowSlip borrowSlip, List<String> bookTitles) {
        // Set borrow slip ID and timestamps
        borrowSlip.setId(UUID.randomUUID().toString());
        borrowSlip.setCreatedAt(LocalDateTime.now());
        borrowSlip.setUpdatedAt(LocalDateTime.now());
        
        // Join book titles with comma
        borrowSlip.setBooks(String.join(", ", bookTitles));
        
        // Save borrow slip
        return borrowSlipRepository.save(borrowSlip);
    }

    @Transactional
    public void updateBookQuantities(List<String> bookIds) {
        for (String bookId : bookIds) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new LibraryException("Book not found with id: " + bookId));
            
            if (book.getAvailableQuantity() <= 0) {
                throw new LibraryException("Book is not available: " + book.getTitle());
            }
            
            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
            book.setUpdatedAt(LocalDateTime.now());
            bookRepository.save(book);
        }
    }
}