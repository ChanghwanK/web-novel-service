package com.web.novel.novel.entity;

import com.web.novel.novel.ChapterPriceInfo;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PriceInfoEntity {

    @Column(name = "policy", nullable = false)
    private String policy;

    @Column(name = "price", nullable = false)
    private int price;

    protected PriceInfoEntity() { }

    private PriceInfoEntity(final String policy, final int price) {
        this.policy = policy;
        this.price = price;
    }

    public static PriceInfoEntity valueOf(final ChapterPriceInfo priceInfo) {
        return new PriceInfoEntity(priceInfo.getPolicy().name(), priceInfo.getValue());
    }
}
