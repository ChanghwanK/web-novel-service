package com.web.novel.point.repository;

import com.web.novel.point.entity.PointJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointJpaEntity, Long> { }
