package com.web.novel.novel;

import com.web.novel.exception.InvalidParamException;
import lombok.Value;

@Value
public class PriceInfo {

    public static final PriceInfo FREE = new PriceInfo(0, Policy.FREE);

    int price;
    Policy policy;

    public enum Policy {
        FREE, PAID
    }

    public static PriceInfo create(String policy, int price) {
        switch(policy) {
            case "FREE":
                return FREE;
            case "PAID":
                return new PriceInfo(price, Policy.PAID);
            default:
                throw new InvalidParamException();
        }
    }
}
