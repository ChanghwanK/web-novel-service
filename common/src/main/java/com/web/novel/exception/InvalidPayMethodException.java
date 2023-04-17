package com.web.novel.exception;

public class InvalidPayMethodException extends BaseException {
    private static final String FORMAT = "잘못된 결제 수단 입니다. %s";
    public InvalidPayMethodException(String message) {
        super(String.format(FORMAT, message));
    }
}
