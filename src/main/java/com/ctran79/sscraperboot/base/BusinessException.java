package com.ctran79.sscraperboot.base;

/**
 * @author ctran79
 */

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
    }
}