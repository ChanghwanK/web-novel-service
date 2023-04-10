package com.web.novel.novel.favorite.entity;


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
@Table(name = "favorites")
public class FavoriteJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "novel_id")
    private Long novelId;

    protected FavoriteJpaEntity() {}

    private FavoriteJpaEntity(Long memberId, Long novelId) {
        this.memberId = memberId;
        this.novelId = novelId;
    }

    public static FavoriteJpaEntity init(Long memberId, Long novelId) {
        return new FavoriteJpaEntity(memberId, novelId);
    }
}
