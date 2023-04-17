package com.web.novel.point;

import com.web.novel.exception.InvalidPayMethodException;

public enum PayMethod {
    KAKAO_PAY,
    NAVER_PAY,
    CARD;

    public static PayMethod create(final String payMethod) {
        switch(payMethod) {
            case "KAKAO_PAY":
                return PayMethod.KAKAO_PAY;
            case "NAVER_PAY":
                return PayMethod.NAVER_PAY;
            case "CARD":
                return PayMethod.CARD;
            default:
                throw new InvalidPayMethodException(payMethod);
        }
    }
}
