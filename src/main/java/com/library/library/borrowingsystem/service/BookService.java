package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.exception.ResourceNotFoundException;
import com.library.library.borrowingsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book addBook(Book book) {
        book.setAvailableQuantity(book.getQuantity());
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return book;
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}