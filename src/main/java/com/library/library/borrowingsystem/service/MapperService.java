package com.library.library.borrowingsystem.service;

import com.library.library.borrowingsystem.dto.BookDTO;
import com.library.library.borrowingsystem.dto.BorrowingRecordDTO;
import com.library.library.borrowingsystem.dto.PatronDTO;
import com.library.library.borrowingsystem.entity.Book;
import com.library.library.borrowingsystem.entity.BorrowingRecord;
import com.library.library.borrowingsystem.entity.Patron;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapperService {
    private final ModelMapper modelMapper;

    public BookDTO toBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public List<BookDTO> toBookDTOs(List<Book> books) {
        return books.stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList());
    }

    public Book toBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public PatronDTO toPatronDTO(Patron patron) {
        return modelMapper.map(patron, PatronDTO.class);
    }

    public List<PatronDTO> toPatronDTOs(List<Patron> patrons) {
        return patrons.stream()
                .map(this::toPatronDTO)
                .collect(Collectors.toList());
    }

    public Patron toPatron(PatronDTO patronDTO) {
        return modelMapper.map(patronDTO, Patron.class);
    }

    public BorrowingRecordDTO toBorrowingRecordDTO(BorrowingRecord record) {
        BorrowingRecordDTO dto = new BorrowingRecordDTO();
        dto.setId(record.getId());
        dto.setBookId(record.getBook().getId());
        dto.setBookTitle(record.getBook().getTitle());
        dto.setPatronId(record.getPatron().getId());
        dto.setPatronName(record.getPatron().getName());
        dto.setBorrowDate(record.getBorrowDate());
        dto.setReturnDate(record.getReturnDate());
        dto.setDueDate(record.getDueDate());
        dto.setStatus(record.getStatus());
        return dto;
    }

    public List<BorrowingRecordDTO> toBorrowingRecordDTOs(List<BorrowingRecord> records) {
        return records.stream()
                .map(this::toBorrowingRecordDTO)
                .collect(Collectors.toList());
    }
}