package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.dto.BookDTO;
import com.library.library.borrowingsystem.dto.BorrowSlipDTO;
import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.entity.BorrowSlip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperService {

    public BookDTO toBookDTO(Book book) {
        if (book == null) return null;
        
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setAvailableQuantity(book.getAvailableQuantity());
        dto.setTotalQuantity(book.getTotalQuantity());
        return dto;
    }

    public Book toBook(BookDTO dto) {
        if (dto == null) return null;
        
        Book book = new Book();
        // Don't set ID when converting from DTO to entity
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailableQuantity(dto.getAvailableQuantity());
        book.setTotalQuantity(dto.getTotalQuantity());
        return book;
    }

    public List<BookDTO> toBookDTOs(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList());
    }

    public BorrowSlipDTO toBorrowSlipDTO(BorrowSlip slip) {
        if (slip == null) return null;
        
        BorrowSlipDTO dto = new BorrowSlipDTO();
        dto.setId(slip.getId());
        dto.setBorrowerName(slip.getBorrowerName());
        dto.setStudentId(slip.getStudentId());
        dto.setContactEmail(slip.getContactEmail());
        dto.setContactPhone(slip.getContactPhone());
        dto.setBooks(slip.getBooks());
        dto.setBorrowDate(slip.getBorrowDate());
        dto.setDueDate(slip.getDueDate());
        dto.setStatus(slip.getStatus());
        dto.setCreatedAt(slip.getCreatedAt());
        dto.setUpdatedAt(slip.getUpdatedAt());
        return dto;
    }

    public BorrowSlip toBorrowSlip(BorrowSlipDTO dto) {
        if (dto == null) return null;
        
        BorrowSlip slip = new BorrowSlip();
        // Don't set ID when converting from DTO to entity
        slip.setBorrowerName(dto.getBorrowerName());
        slip.setStudentId(dto.getStudentId());
        slip.setContactEmail(dto.getContactEmail());
        slip.setContactPhone(dto.getContactPhone());
        slip.setBooks(dto.getBooks());
        slip.setBorrowDate(dto.getBorrowDate());
        slip.setDueDate(dto.getDueDate());
        slip.setStatus(dto.getStatus());
        return slip;
    }

    public List<BorrowSlipDTO> toBorrowSlipDTOs(List<BorrowSlip> slips) {
        if (slips == null) return null;
        return slips.stream()
                .map(this::toBorrowSlipDTO)
                .collect(Collectors.toList());
    }
}