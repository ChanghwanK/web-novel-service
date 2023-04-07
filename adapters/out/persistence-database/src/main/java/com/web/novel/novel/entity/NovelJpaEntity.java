package com.web.novel.novel.entity;

import com.web.novel.BaseEntity;
import com.web.novel.exception.InvalidParamException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
@Entity
@Table(
    name = "novels",
    indexes = {
        @Index(name = "uq_title", columnList = "title", unique = true)
    }
)
public class NovelJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "author_nickName", nullable = false)
    private String authorNickName;

    @Column(columnDefinition = "LONGTEXT")
    private String synopsis;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Embedded
    private SerialInfoJpaEntity serialInfoJpaEntity;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "novel", cascade = CascadeType.ALL)
    private final List<TagJpaEntity> tags = new ArrayList<>();

    protected NovelJpaEntity() { }

    private enum Status {
        OPEN, DELETE;
    }

    public NovelJpaEntity(
            final String title,
            final String coverImageUrl,
            final String authorNickName,
            final SerialInfoJpaEntity serialInfoJpaEntity,
            final String synopsis,
            final Long genreId,
            final Long memberId) {

        parameterValidation(title, coverImageUrl, authorNickName, synopsis, genreId, memberId);

        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.authorNickName = authorNickName;
        this.serialInfoJpaEntity = serialInfoJpaEntity;
        this.status = Status.OPEN;
        this.synopsis = synopsis;
        this.genreId = genreId;
        this.memberId = memberId;
    }

    private static void parameterValidation(String title, String coverImageUrl, String authorNickName,
        String synopsis, Long genreId, Long memberId) {
        if(ObjectUtils.isEmpty(title))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(coverImageUrl))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(authorNickName))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(synopsis))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(title))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(genreId))
            throw new InvalidParamException();

        if(ObjectUtils.isEmpty(memberId))
            throw new InvalidParamException();
    }

    public void addTagEntities(List<TagJpaEntity> tagJpaEntities) {
        for(var tag : tagJpaEntities) tag.setNovelJpaEntity(this);
    }

    public NovelJpaEntity delete() {
        this.status = Status.DELETE;
        return this;
    }
}
