package com.ashen_dissanayake.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler({IllegalArgumentException.class})
   public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException ex, WebRequest request) {
      ErrorResponse errorResponse = new ErrorResponse(
              HttpStatus.BAD_REQUEST.value(),
              ex.getMessage(),
              request.getDescription(false)
      );

      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
      ErrorResponse errorResponse = new ErrorResponse(
              HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "Unexpected error occurred",
              request.getDescription(false)
      );

      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
      String errorMessage = ex.getBindingResult().getFieldErrors().stream()
              .map(err -> err.getDefaultMessage())
              .collect(Collectors.joining(""));

      ErrorResponse errorResponse = new ErrorResponse(
              HttpStatus.BAD_REQUEST.value(),
              errorMessage,
              request.getDescription(false)
      );

      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(IllegalStateException.class)
   public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex, WebRequest request) {
      ErrorResponse error = new ErrorResponse(
              HttpStatus.CONFLICT.value(),
              ex.getMessage(),
              request.getDescription(false)
      );
      return new ResponseEntity<>(error, HttpStatus.CONFLICT);
   }
}
