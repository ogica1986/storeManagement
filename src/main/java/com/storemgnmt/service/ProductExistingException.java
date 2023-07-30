package com.storemgnmt.service;

public class ProductExistingException extends RuntimeException{
    public ProductExistingException(String message) {
        super(message);
    }
}
