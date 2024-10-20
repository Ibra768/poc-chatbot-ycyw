package com.ycyw.chatbot.controller.handler;


import com.ycyw.chatbot.dto.ResponseDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.security.auth.message.AuthException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * Handle all native exceptions
     * EntityExistsException: Throwed when a user already exists
     * DataIntegrityViolationException: Throwed when a user already exists
     * BadCredentialsException: Throwed when a user try to authenticate with bad credentials
     * ConstraintViolationException: Throwed when a constraint is violated (@NotNull, @NotEmpty, etc.)
     * Exception: Other exceptions
     */
    @ExceptionHandler(value = { EntityExistsException.class, DataIntegrityViolationException.class, BadCredentialsException.class, ConstraintViolationException.class, Exception.class })
    protected ResponseEntity<?> handleException(Exception ex) {

        // Most of the time, the error is a bad request
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof EntityExistsException || ex instanceof ConstraintViolationException || ex instanceof DataIntegrityViolationException) {
            return ResponseEntity.status(status).body(new ResponseDTO(ex.getMessage()));
        }
        else{
            if(ex instanceof BadCredentialsException){
                status = HttpStatus.UNAUTHORIZED;
            }
            return ResponseEntity.status(status).body(new ResponseDTO(ex.getMessage()));
        }

    }
    @ExceptionHandler(value = { AuthException.class })
    protected ResponseEntity<?> handleBadRequestException(AuthException ex) {
        return ResponseEntity.status(400).body(new ResponseDTO(ex.getMessage()));
    }
}