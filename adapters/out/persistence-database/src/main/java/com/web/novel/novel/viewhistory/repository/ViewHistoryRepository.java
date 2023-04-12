package com.web.novel.novel.viewhistory.repository;

import com.web.novel.novel.viewhistory.entity.ViewHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistoryJpaEntity, Long> {

}
