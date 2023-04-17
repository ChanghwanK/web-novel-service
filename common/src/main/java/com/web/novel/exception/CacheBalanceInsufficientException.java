package com.web.novel.exception;

public class CacheBalanceInsufficientException extends BaseException {

    private static final String FORMAT = "캐시 잔액이 부족합니다. 잔액: %s";

    public CacheBalanceInsufficientException(int balance) {
        super(String.format(FORMAT, balance));
    }
}
