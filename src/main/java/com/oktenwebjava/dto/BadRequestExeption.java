package com.oktenwebjava.dto;

public class BadRequestExeption extends RuntimeException {

    public BadRequestExeption(String message) {
        super(message);
    }
}
