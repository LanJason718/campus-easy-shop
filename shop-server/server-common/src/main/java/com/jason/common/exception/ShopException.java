package com.jason.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ShopException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ShopException() {}

    public ShopException(String message) {
        super(message);
    }
}