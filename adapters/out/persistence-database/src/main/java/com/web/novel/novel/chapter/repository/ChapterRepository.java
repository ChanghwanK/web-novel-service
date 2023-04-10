package com.web.novel.novel.chapter.repository;

import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChapterRepository extends JpaRepository<ChapterJpaEntity, Long> {

    @Query(value = "select * from chapters c" +
        " order by c.ordering desc" +
        " limit 1", nativeQuery = true)
    Optional<ChapterJpaEntity> findChapterOrderByIdLimitOne();
}
