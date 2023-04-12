package com.web.novel.novel.viewhistory.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "view_history")
public class ViewHistoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "novel_id", nullable = false)
    private Long novelId;

    @Column(name = "member_id", nullable =false)
    private Long memberId;

    @Comment("본 시간")
    @Column(name = "viewed_at")
    private LocalDateTime viewedAt;

    protected ViewHistoryJpaEntity() { }

    public ViewHistoryJpaEntity(Long id, Long novelId, Long memberId, LocalDateTime viewedAt) {
        this.id = id;
        this.novelId = novelId;
        this.memberId = memberId;
        this.viewedAt = viewedAt;
    }
}
