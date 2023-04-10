package com.web.novel.exception;

public class SynopsisWordSizeOverException extends BaseException {
    private static final String FORMAT = "시놉시스 글자수가 초가되었습니다. Size: %s";
    public SynopsisWordSizeOverException(int size) {
        super(String.format(FORMAT,  size));
    }
}
