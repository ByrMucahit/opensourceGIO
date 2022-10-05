package com.example.opensourcegio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRepositoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDuplicatedRepositoryException(DuplicatedRepositoryException exception, HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder().message(exception.getMessage()).build();
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder().message(exception.getMessage()).build();
    }
}
