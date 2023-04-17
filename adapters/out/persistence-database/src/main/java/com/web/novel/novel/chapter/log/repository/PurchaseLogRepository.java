package com.web.novel.novel.chapter.log.repository;

import com.web.novel.novel.chapter.log.entity.ChapterPurchaseLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseLogRepository extends JpaRepository<ChapterPurchaseLogJpaEntity, Long> {

}
