package com.web.novel.novel.chapter.log.entity;

import com.web.novel.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chapter_purchase_logs")
public class ChapterPurchaseLogJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long novelId;
    private Long chapterId;
    private int chapterPrice;

    protected ChapterPurchaseLogJpaEntity() {}

    private ChapterPurchaseLogJpaEntity(
            final Long memberId,
            final Long novelId,
            final Long chapterId,
            final int chapterPrice) {
        this.memberId = memberId;
        this.novelId = novelId;
        this.chapterId = chapterId;
        this.chapterPrice = chapterPrice;
    }

    public static ChapterPurchaseLogJpaEntity createForSave(
            final Long memberId,
            final Long novelId,
            final Long chapterId,
            final int chapterPrice) {
        return new ChapterPurchaseLogJpaEntity(memberId, novelId, chapterId, chapterPrice);
    }
}
