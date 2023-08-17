package com.storemgnmt.controller;

import com.storemgnmt.service.ProductExistingException;
import com.storemgnmt.service.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StoreExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ProductExistingException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String productExistingHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }
}
