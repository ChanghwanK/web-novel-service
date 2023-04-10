package com.web.novel.novel.repository;

import com.web.novel.novel.entity.TagJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagJpaEntity, Long> {

}
