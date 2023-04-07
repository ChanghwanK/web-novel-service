package com.web.novel.exception;

public class TagSizeInvalidException extends BaseException {
    private static final String FORMAT = "태그 Size가 잘못되었습니다. Tag Szie: %s";
    public TagSizeInvalidException(int tagSize) {
        super(String.format(FORMAT, tagSize));
    }
}
