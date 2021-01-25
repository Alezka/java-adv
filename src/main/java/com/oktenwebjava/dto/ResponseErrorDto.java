package com.oktenwebjava.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ResponseErrorDto {
    private int status;
    @JsonFormat(pattern = "yyyy/MM  dd:mm")
    private LocalDateTime timestamp;
    private String message;
}

