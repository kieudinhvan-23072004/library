package com.library.library.borrowingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BorrowingLimitExceededException extends RuntimeException {
    public BorrowingLimitExceededException(String message) {
        super(message);
    }
}