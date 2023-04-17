package com.web.novel.exception;

public class DuplicatedPointNumberException extends BaseException {
    private final static String FORMAT = "이미 등록된 Point 충전 요청입니다. %s";
    public DuplicatedPointNumberException(String message) {
        super(String.format(FORMAT, message));
    }
}
