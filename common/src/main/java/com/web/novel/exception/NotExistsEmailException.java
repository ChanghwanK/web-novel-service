package com.web.novel.exception;

public class NotExistsEmailException extends BaseException {
    private static final String FORMAT = "존재하지 않는 Entity 입니다. Email: %s";
    public NotExistsEmailException(String email) {
        super(String.format(FORMAT, email));
    }
}
