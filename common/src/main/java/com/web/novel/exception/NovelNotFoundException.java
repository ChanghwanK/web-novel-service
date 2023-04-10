package com.web.novel.exception;

public class NovelNotFoundException extends BaseException {
    private final static String FORMAT = "잘못된 소설 ID 입니다. ID: %s";
    public NovelNotFoundException(Long id) {
        super(String.format(FORMAT, id));
    }
}
