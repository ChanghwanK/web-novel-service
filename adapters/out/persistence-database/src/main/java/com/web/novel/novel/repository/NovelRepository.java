package com.web.novel.novel.repository;

import com.web.novel.novel.entity.NovelJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NovelRepository extends JpaRepository<NovelJpaEntity, Long> {
    // todo join 쿼리짜기
    @Query("select novel from NovelJpaEntity novel where novel.id = : novelId")
    Optional<NovelJpaEntity> findByIdWithRecentUploadedChapter(@Param("novelId") Long novelId);
}
