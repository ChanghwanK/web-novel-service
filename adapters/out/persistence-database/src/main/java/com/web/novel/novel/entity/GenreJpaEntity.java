package com.web.novel.novel.entity;

import com.web.novel.exception.InvalidParamException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
@Entity
@Table(name = "genres")
public class GenreJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    protected GenreJpaEntity() {}

    public GenreJpaEntity(final String name) {
        if(ObjectUtils.isEmpty(name))
            throw new InvalidParamException(name);
        this.name = name;
    }
}
