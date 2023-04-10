package com.web.novel.novel.entity;

import com.web.novel.exception.InvalidParamException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import org.springframework.util.ObjectUtils;


@Getter
@Entity
@Table(name = "tags")
public class TagJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "novels_id")
    private NovelJpaEntity novel;

    protected TagJpaEntity() {}

    public TagJpaEntity(final String name) {
        if(ObjectUtils.isEmpty(name))
            throw new InvalidParamException(name);
        this.name = name;
    }

    public void setNovelJpaEntity(NovelJpaEntity novelJpaEntity) {
        this.novel = novelJpaEntity;
    }
}
