package com.web.novel.novel.chapter.log;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.novel.chapter.ChapterPurChaseLog;
import com.web.novel.novel.chapter.log.mapper.PurchaseLogMapper;
import com.web.novel.novel.chapter.log.repository.PurchaseLogRepository;
import com.web.novel.novel.chapter.port.out.ChapterPurchaseLogSavePort;

@PersistenceAdapter
public class ChapterLogPersistenceAdapter implements ChapterPurchaseLogSavePort {

    private final PurchaseLogMapper purchaseLogMapper;
    private final PurchaseLogRepository purchaseLogRepository;

    public ChapterLogPersistenceAdapter(
            final PurchaseLogMapper purchaseLogMapper,
            final PurchaseLogRepository purchaseLogRepository) {
        this.purchaseLogMapper = purchaseLogMapper;
        this.purchaseLogRepository = purchaseLogRepository;
    }

    @Override
    public void save(ChapterPurChaseLog chapterPurchaseLog) {
        var initEntity = purchaseLogMapper.mapToJpaEntity(
            chapterPurchaseLog.getMemberId().getValue(),
            chapterPurchaseLog.getNovelId().getValue(),
            chapterPurchaseLog.getChapterId().getValue(),
            chapterPurchaseLog.getChapterPriceInfo().getValue());

        purchaseLogRepository.save(initEntity);
    }
}
