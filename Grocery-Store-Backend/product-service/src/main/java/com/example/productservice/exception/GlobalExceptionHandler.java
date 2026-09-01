package com.example.productservice.exception;

import com.example.productservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDto> handleValidationException(
                MethodArgumentNotValidException ex,
                HttpServletRequest request) {

            String message = ex.getBindingResult()
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();

            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .timestamp(LocalDateTime.now().toString())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(message)
                    .path(request.getRequestURI())
                    .build();


            return ResponseEntity.badRequest().body(errorResponse);

    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDto> handleStrockException(
            InsufficientStockException ex,
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

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> categorynotfound(
            CategoryNotFoundException ex,
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

    @ExceptionHandler(DuplicateskuException.class)
    public ResponseEntity<ErrorResponseDto> duplicateSKU(
           DuplicateskuException ex,
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
    public ResponseEntity<ErrorResponseDto> productNotException(
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

    @ExceptionHandler(ResponseAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> methodValidationExceptions(
            ResponseAlreadyExistException ex,
            HttpServletRequest request) {

       ErrorResponseDto response = ErrorResponseDto.builder()
               .timestamp(LocalDateTime.now().toString())
               .status(HttpStatus.NOT_FOUND.value())
               .error(HttpStatus.NOT_FOUND.getReasonPhrase())
               .message(ex.getMessage())
               .path(request.getRequestURI())
               .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<ErrorResponseDto> InvalideException(
            InvalidPriceException ex,
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
