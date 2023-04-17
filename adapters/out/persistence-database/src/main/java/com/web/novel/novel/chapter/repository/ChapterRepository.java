package com.web.novel.novel.chapter.repository;

import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapterRepository extends JpaRepository<ChapterJpaEntity, Long> {

    @Query(value = "select * from chapters c" +
        " order by c.ordering desc" +
        " limit 1", nativeQuery = true)
    Optional<ChapterJpaEntity> findChapterOrderByIdLimitOne();

    @Query(value = "select c from ChapterJpaEntity c " +
        "where c.novelId = :novelId " +
        "order by c.ordering desc")
    List<ChapterJpaEntity> findChapterByNovelIdOrderByOrderingDESC(@Param("novelId") Long novelId, Pageable pageable);
}
