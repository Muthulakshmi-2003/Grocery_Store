package com.example.orderservice.exception;

import com.example.orderservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDto> InsufficientException(
            InsufficientStockException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> OrderNotFound(
            OrderNotFoundException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<ErrorResponseDto> InvalideStatusFound(
            InvalidOrderStatusException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConfirmedOrderCannotCancellException.class)
    public ResponseEntity<ErrorResponseDto> ConfirmedOrderCannotCancel(
          ConfirmedOrderCannotCancellException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProductInactiveException.class)
    public ResponseEntity<ErrorResponseDto> ProductInactive(
            ProductInactiveException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> ProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponseDto> InvalidQuantityException(
            InvalidQuantityException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
