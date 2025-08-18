package com.oms.orderservice.exception;

import com.oms.orderservice.util.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GenericExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionHandler.class);

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Invalid request path"));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Invalid request body structure"));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error(e.getMessage());
        String errors = e.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(String.format("Invalid request path/query parameters: %s", errors)));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage());
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(v -> v.getField() + ": " + v.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(String.format("Invalid request body parameters: %s", errors)));
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        LOGGER.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Something went wrong"));
    }

}