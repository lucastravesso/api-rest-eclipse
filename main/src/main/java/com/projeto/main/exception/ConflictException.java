package com.projeto.main.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends WebException {

    private static final long serialVersionUID = 1333963335839262120L;

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message, ExceptionCode.CLASS_EQUALS_VALUE);
    }
}
