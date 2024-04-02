package com.tsk.practice.exceptions;

import com.tsk.practice.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobaleExceptionHandler {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundResourceException ex, HttpServletRequest request) {
        String message = ex.getEntityName() + " " + ex.getEntityId() + " not found. ";
        Response<ExceptionEntity> response = new Response<>(new ExceptionEntity(ex.getEntityName(), message, request.getRequestURI()))
                .status(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.name())
                .has_error(true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        Response<ExceptionEntity> response = new Response<>(new ExceptionEntity(ex.getMessage(), request.getRequestURI()))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .has_error(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<?>> handleValidationException(BindException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            List<String> messages = new ArrayList<>();
            if (errors.containsKey(fieldName)) {
                messages = errors.get(fieldName);
            }
            messages.add(error.getDefaultMessage());
            errors.put(fieldName, messages);
        });

        Response<?> response = new Response<>(errors)
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .has_error(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
