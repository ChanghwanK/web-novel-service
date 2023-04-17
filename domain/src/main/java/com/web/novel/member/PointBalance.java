package com.web.novel.member;

import lombok.Value;

@Value
public class PointBalance {
    public static final PointBalance ZERO = new PointBalance(0);

    int value;

    public PointBalance charge(Integer value) {
        return new PointBalance(this.value + value);
    }
}
