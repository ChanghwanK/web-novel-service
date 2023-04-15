package com.web.novel.member;

import com.web.novel.exception.CacheBalanceInsufficientException;
import lombok.Value;

@Value
public class Cache {

    public static final Cache ZERO = new Cache(0);
    Integer balance;

    public Cache purchase(Integer chapterPrice) {
        if(balance < chapterPrice)
            throw new CacheBalanceInsufficientException(balance);

        int remain = balance - chapterPrice;

        return new Cache(remain);
    }
}
