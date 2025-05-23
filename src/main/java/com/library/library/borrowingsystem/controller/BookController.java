package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.dto.BookDTO;
import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.service.BookService;
import com.library.library.borrowingsystem.service.BorrowingService;
import com.library.library.borrowingsystem.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(mapperService.toBookDTOs(books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(mapperService.toBookDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = mapperService.toBook(bookDTO);
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.ok(mapperService.toBookDTO(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable String id,
            @RequestBody BookDTO bookDTO
    ) {
        Book book = mapperService.toBook(bookDTO);
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(mapperService.toBookDTO(updatedBook));
    }

    @PutMapping("/update-quantities")
    public ResponseEntity<Void> updateBookQuantities(@RequestBody List<String> bookIds) {
        borrowingService.updateBookQuantities(bookIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}