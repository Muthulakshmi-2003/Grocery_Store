package com.example.inventoryservice.exception;

import com.example.inventoryservice.Constant.TimeConstant;
import com.example.inventoryservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArugumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
            IllegalArugumentException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDto> insufficientException(
            InsufficientStockException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> inventoryNotFound(
            InventoryNotFoundException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(StockMovementNotFound.class)
    public ResponseEntity<ErrorResponseDto> stockeMovemntNotFound(
            StockMovementNotFound ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InventoryAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> inventoryAlreadyExists(
            InventoryAlreadyExists ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponseDto> invalidQuantityException(
            InvalidQuantityException ex,
            HttpServletRequest request)
    {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now(TimeConstant.INDIA_ZONE).toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
