package com.web.novel.novel;

import com.web.novel.exception.InvalidParamException;
import lombok.Value;

@Value
public class ChapterPriceInfo {

    public static final ChapterPriceInfo FREE = new ChapterPriceInfo(0, Policy.FREE);

    int value;
    Policy policy;

    public enum Policy {
        FREE, PAID
    }

    public static ChapterPriceInfo create(String policy, int price) {
        switch(policy) {
            case "FREE":
                return FREE;
            case "PAID":
                return new ChapterPriceInfo(price, Policy.PAID);
            default:
                throw new InvalidParamException();
        }
    }
}
