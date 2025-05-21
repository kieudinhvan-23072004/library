package com.library.library.borrowingsystem.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private Integer quantity;
    private Integer availableQuantity;
}