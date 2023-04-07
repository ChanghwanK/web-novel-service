package com.web.novel.novel.chapter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "chapters")
public class ChapterJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cotent", nullable = false)
    private String content;

    @Column(name = "author_talk", nullable = false)
    private String authorTalk;

    @Column(name = "ordering")
    private Integer ordering;

    @Column(name = "novel_id", nullable = false)
    private Long novelId;

    protected ChapterJpaEntity() {}

    public ChapterJpaEntity(
            final Long id,
            final String title,
            final String content,
            final String authorTalk,
            final Integer ordering,
            final Long novelId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorTalk = authorTalk;
        this.ordering = ordering;
        this.novelId = novelId;
    }
}
