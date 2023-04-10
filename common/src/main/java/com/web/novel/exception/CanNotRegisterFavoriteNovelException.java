package com.web.novel.exception;

public class CanNotRegisterFavoriteNovelException extends BaseException {

    private static final String MSG = "회차가 등록되지 않은 작품으로 선호작으로 등록할 수 없습니다.";

    public CanNotRegisterFavoriteNovelException() {
        super(MSG);
    }
}
