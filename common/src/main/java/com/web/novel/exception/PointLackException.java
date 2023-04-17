package com.web.novel.exception;

public class PointLackException extends BaseException {
    private static final String FORMAT = "포인트가 부족합니다. %s";

    public PointLackException(int value) {
        super(String.format(FORMAT, value));
    }
}
