package com.web.novel.novel.history.repository;

import com.web.novel.novel.history.entity.ViewHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistoryJpaEntity, Long> {

}
