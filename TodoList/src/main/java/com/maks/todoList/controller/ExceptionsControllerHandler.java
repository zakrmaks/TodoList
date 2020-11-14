package com.maks.todoList.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.function.ObjLongConsumer;

@ControllerAdvice
public class ExceptionsControllerHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<NotFoundException> handleNoSuchElement() {

//
        return new ResponseEntity<>(new NotFoundException(HttpStatus.NOT_FOUND, "id not found"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class NotFoundException {
        private HttpStatus httpStatus;
        private String message;
    }
}
