package com.library.library.borrowingsystem.dto;

import com.library.library.borrowingsystem.entity.BorrowSlip;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BorrowRequestDTO {
    private BorrowSlip borrowSlip;
    private List<String> bookTitles;
} 