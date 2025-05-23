package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.exception.LibraryException;
import com.library.library.borrowingsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new LibraryException("Book not found with id: " + id));
    }

    @Transactional
    public Book createBook(Book book) {
        // Validate book data
        validateBook(book);

        // Set new book properties
        book.setId(UUID.randomUUID().toString());
        book.setAvailableQuantity(book.getTotalQuantity()); // Initially all books are available
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(String id, Book book) {
        // Validate book data
        validateBook(book);

        Book existingBook = getBookById(id);
        
        // Update book properties
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        
        // Handle quantity updates
        if (book.getTotalQuantity() < existingBook.getTotalQuantity() - existingBook.getAvailableQuantity()) {
            throw new LibraryException("Cannot reduce total quantity below number of borrowed books");
        }
        
        int borrowedBooks = existingBook.getTotalQuantity() - existingBook.getAvailableQuantity();
        existingBook.setTotalQuantity(book.getTotalQuantity());
        existingBook.setAvailableQuantity(book.getTotalQuantity() - borrowedBooks);
        existingBook.setUpdatedAt(LocalDateTime.now());

        return bookRepository.save(existingBook);
    }

    @Transactional
    public void deleteBook(String id) {
        Book book = getBookById(id);
        
        // Check if any books are currently borrowed
        if (book.getAvailableQuantity() < book.getTotalQuantity()) {
            throw new LibraryException("Cannot delete book while copies are borrowed");
        }
        
        bookRepository.deleteById(id);
    }

    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new LibraryException("Book title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new LibraryException("Book author is required");
        }
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new LibraryException("Book ISBN is required");
        }
        if (book.getTotalQuantity() == null || book.getTotalQuantity() < 1) {
            throw new LibraryException("Book total quantity must be at least 1");
        }
    }
}