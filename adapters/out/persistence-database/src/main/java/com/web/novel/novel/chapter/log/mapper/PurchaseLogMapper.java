package com.web.novel.novel.chapter.log.mapper;

import com.web.novel.novel.chapter.log.entity.ChapterPurchaseLogJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchaseLogMapper {
    public ChapterPurchaseLogJpaEntity mapToJpaEntity(
            final Long memberId,
            final Long novelId,
            final Long chapterId,
            final int chapterPrice) {
        return ChapterPurchaseLogJpaEntity.createForSave(memberId, novelId, chapterId, chapterPrice);
    }
}
