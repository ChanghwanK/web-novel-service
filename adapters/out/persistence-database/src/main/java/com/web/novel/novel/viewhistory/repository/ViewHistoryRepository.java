package com.web.novel.novel.repository;

import com.web.novel.novel.viewhistory.ViewHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistoryJpaEntity, Long> {

}
