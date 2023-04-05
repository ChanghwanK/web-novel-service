package com.web.novel.exception;


public class BaseException extends RuntimeException {
    private final String message;

    public BaseException(String message) {
        this.message = message;
    }
}
