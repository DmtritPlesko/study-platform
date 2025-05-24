package com.study.platform.handler;

import com.study.interaction.exception.ConflictException;
import com.study.interaction.exception.UserNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(Exception exception) {
        String cause = "Ошибка при валидации данных";
        log.info("{}: {}", cause, exception.getMessage());
        return ApiError.builder()
                .message(exception.getMessage())
                .reason(cause)
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(NotFoundException exception) {
        String cause = "Ошибка при поиске данных";
        log.info("{}: {}", cause, exception.getMessage());
        return ApiError.builder()
                .message(exception.getMessage())
                .reason(cause)
                .status(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }
}