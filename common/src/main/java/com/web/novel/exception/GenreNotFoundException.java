package com.web.novel.exception;

public class GenreNotFoundException extends BaseException {
    private static final String FORMAT = "잘못된 장르 Id 입니다. Id: %s";
    public GenreNotFoundException(Long genreId) {
        super(String.format(FORMAT, genreId));
    }
}
