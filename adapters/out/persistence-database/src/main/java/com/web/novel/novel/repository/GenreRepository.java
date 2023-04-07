package com.web.novel.novel.repository;

import com.web.novel.novel.entity.GenreJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreJpaEntity, Long> {

}
