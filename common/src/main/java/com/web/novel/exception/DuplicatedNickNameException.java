package com.web.novel.exception;

public class DuplicatedNickNameException extends BaseException {
    private static final String format = "중복된 닉네임 입니다. Nick Name: %s";
    public DuplicatedNickNameException(String nickName) {
        super(String.format(format, nickName));
    }
}
