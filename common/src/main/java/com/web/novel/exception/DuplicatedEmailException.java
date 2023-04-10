package com.web.novel.exception;

public class DuplicatedEmailException extends BaseException {
    private static final String format = "중복된 이메일 입니다. Email: %s";
    public DuplicatedEmailException(String email) {
        super(String.format(format, email));
    }
}
