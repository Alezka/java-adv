package com.oktenwebjava.controller;

import com.oktenwebjava.dto.BadRequestExeption;
import com.oktenwebjava.dto.ResponseErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseErrorDto hendleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = parseValidationExeption(ex);
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), message);
    }

    @ExceptionHandler(value = BadRequestExeption.class)
    public ResponseErrorDto handleBadRequestExeption(Exception ex) {
        log.info("Handled BadRequestExeption: {}",ex.getMessage());
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage());
    }

    private String parseValidationExeption(MethodArgumentNotValidException ex) {
        List<String> defaultMessages = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return defaultMessages.toString();
    }
}
