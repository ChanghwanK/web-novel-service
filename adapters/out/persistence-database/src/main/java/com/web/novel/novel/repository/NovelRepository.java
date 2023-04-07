package com.web.novel.novel.repository;

import com.web.novel.novel.entity.NovelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<NovelJpaEntity, Long> {

}
