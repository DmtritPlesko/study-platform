package com.study.platform.exception.hendler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LocalExceptionHandler {

    // обработка @Valid
    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleValidationException (
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse errorResponse = new ErrorResponse("Ошибка валидации", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
