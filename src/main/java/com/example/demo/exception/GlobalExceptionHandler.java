
package com.example.demo.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new LinkedHashMap<>();

        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Validation Failed");

        Map<String, String> fieldErrors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        errors.put("messages", fieldErrors);

        return new ResponseEntity<>(
                errors, HttpStatus.BAD_REQUEST);
    }

    // 2. Handle student not found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStudentNotFound(
            StudentNotFoundException ex) {

        Map<String, Object> error = new LinkedHashMap<>();

        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Student Not Found");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(
                error, HttpStatus.NOT_FOUND);
    }

    // 3. Handle duplicate student ID
    @ExceptionHandler(DuplicateStudentException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateStudent(
            DuplicateStudentException ex) {

        Map<String, Object> error = new LinkedHashMap<>();

        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", "Duplicate Student");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(
                error, HttpStatus.CONFLICT);
    }

    // 4. Handle illegal arguments
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex) {

        Map<String, Object> error = new LinkedHashMap<>();

        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Bad Request");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(
                error, HttpStatus.BAD_REQUEST);
    }

    // 5. Handle all other unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(
            Exception ex) {

        Map<String, Object> error = new LinkedHashMap<>();

        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", "An unexpected error occurred");

        return new ResponseEntity<>(
                error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
