package com.web.novel.novel.history.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="view_history")
public class ViewHistoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "novel_id")
    private Long novelId;

    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected ViewHistoryJpaEntity() { }

    public ViewHistoryJpaEntity(
            final Long memberId,
            final Long novelId,
            final Long chapterId) {
        this.memberId = memberId;
        this.novelId = novelId;
        this.chapterId = chapterId;
        this.createdAt = LocalDateTime.now();
    }
}
