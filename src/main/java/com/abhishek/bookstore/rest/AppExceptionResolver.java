package com.abhishek.bookstore.rest;

import java.util.stream.Collectors;

import com.abhishek.bookstore.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class AppExceptionResolver {


    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception exception, WebRequest request) {
        log.error("Error while serving url:{}", request.getContextPath(), exception);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage();
        if (exception instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
            message = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors()
                    .stream().map(ObjectError::toString).collect(Collectors.joining(","));
        }
        return new ResponseEntity<>(new ErrorResponse(status.getReasonPhrase(), message), status);
    }
}
