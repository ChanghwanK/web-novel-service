package com.web.novel.novel.recentread.repository;

import com.web.novel.novel.recentread.entity.RecentReadNovelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentReadNovelRepository extends JpaRepository<RecentReadNovelJpaEntity, Long> { }
