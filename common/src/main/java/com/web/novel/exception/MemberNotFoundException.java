package com.web.novel.exception;

public class MemberNotFoundException extends BaseException {
    private final static String FORMAT = "해당 ID를 가진 회원을 찾지 못했습니다. ID: %s";

    public MemberNotFoundException(Long memberId) {
        super(String.format(FORMAT, memberId));
    }
}
