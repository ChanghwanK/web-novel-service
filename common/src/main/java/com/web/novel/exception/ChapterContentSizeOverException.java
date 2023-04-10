package com.web.novel.exception;

public class ChapterContentSizeOverException extends BaseException {
    private static final String FORMAT = "Content Size가 Over 되었습니다. Size: %s";
    public ChapterContentSizeOverException(int size) {
        super(String.format(FORMAT, size));
    }
}
