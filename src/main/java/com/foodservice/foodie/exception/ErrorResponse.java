package com.foodservice.foodie.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
    private String details;
}
