package com.web.novel.member;

import com.web.novel.exception.PointLackException;
import lombok.Value;

@Value
public class PointBalance {
    public static final PointBalance ZERO = new PointBalance(0);

    int value;

    public PointBalance charge(final Integer value) {
        return new PointBalance(this.value + value);
    }

    public PointBalance purchase(final Integer price) {
        if(value < price)
            throw new PointLackException(value);


        return new PointBalance(value - price);
    }
}
