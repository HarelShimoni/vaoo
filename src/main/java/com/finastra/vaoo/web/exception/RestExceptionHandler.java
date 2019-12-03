package com.finastra.vaoo.web.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (value = {EntityNotFoundException.class, EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        return resUtil(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler (value = {SecurityException.class})
    protected ResponseEntity<Object> handleConflictSecurity(
            RuntimeException ex, WebRequest request) {
        return resUtil(ex, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler (value = {MissingRequestHeaderException.class})
    protected ResponseEntity<Object> handleMissingHeaderProperty(RuntimeException ex, WebRequest request) {
        return resUtil(ex, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> resUtil (RuntimeException ex, HttpStatus status,  WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (status == HttpStatus.NOT_FOUND) {
            String bodyOfResponse = Optional.ofNullable(ex.getMessage()).orElse("Entity was not found");
            return handleExceptionInternal(ex, bodyOfResponse,
                headers, status, request);
        } else {
            String bodyOfResponse = String.format(ex.getMessage());
            return handleExceptionInternal(ex, bodyOfResponse,
                headers, status, request);
        }
    }
}

