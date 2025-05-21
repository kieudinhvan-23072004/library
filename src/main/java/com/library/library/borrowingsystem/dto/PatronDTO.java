package com.library.library.borrowingsystem.dto;

import lombok.Data;

@Data
public class PatronDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}