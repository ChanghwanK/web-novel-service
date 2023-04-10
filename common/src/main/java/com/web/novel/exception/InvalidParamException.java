package com.web.novel.exception;

public class InvalidParamException extends BaseException {
    private static final String FORMAT = "유효하지 않은 Paramter 입니다. %s";

    public InvalidParamException() {
        super("Entity Parameter is never null");
    }

    public InvalidParamException(String message) {
        super(String.format(FORMAT, message));
    }
}
