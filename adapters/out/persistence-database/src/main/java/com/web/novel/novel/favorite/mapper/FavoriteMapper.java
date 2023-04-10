package com.web.novel.novel.favorite.mapper;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.favorite.Favorite;
import com.web.novel.novel.favorite.Favorite.FavoriteId;
import com.web.novel.novel.favorite.entity.FavoriteJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {
    public Favorite mapToDomain(final FavoriteJpaEntity favoriteJpaEntity) {
        return new Favorite(
            new FavoriteId(favoriteJpaEntity.getId()),
            new MemberId(favoriteJpaEntity.getMemberId()),
            new NovelId(favoriteJpaEntity.getNovelId()));
    }
}
