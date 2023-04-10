package com.web.novel.novel.recentread.entity;

import com.web.novel.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "recent_read_novels")
public class RecentReadNovelJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "novel_id", nullable = false)
    private Long novelId;

    @Column(name = "chapter_id", nullable = false)
    private Long chapterId;
}
