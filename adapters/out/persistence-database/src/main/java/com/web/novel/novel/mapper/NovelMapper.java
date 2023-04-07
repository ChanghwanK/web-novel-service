package com.web.novel.novel.mapper;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.AuthorInfo.AuthorId;
import com.web.novel.novel.Genre;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.Novel;
import com.web.novel.novel.NovelMetaInfo;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.entity.NovelJpaEntity;
import com.web.novel.novel.entity.SerialInfoJpaEntity;
import com.web.novel.novel.entity.TagJpaEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class NovelMapper {
    public NovelJpaEntity mapToJpaEntity(
            final Novel novel,
            final String authorNickName,
            final Long genreId) {

       var tagJpaEntities = novel.getTags()
            .stream()
            .map(this::mapToTagEntity)
            .collect(Collectors.toList());

        var serialInfoJpaEntity = SerialInfoJpaEntity.init(novel.getSerialInfo());
        var initNovelJpaEntity = initNovelJpaEntity(novel, authorNickName, serialInfoJpaEntity, genreId);
        initNovelJpaEntity.addTagEntities(tagJpaEntities);

        return initNovelJpaEntity;
    }

    public Novel mapToDomain(final NovelJpaEntity novelJpaEntity) {
        return new Novel(
            new NovelMetaInfo(novelJpaEntity.getTitle(), novelJpaEntity.getCoverImageUrl()),
            Genre.init(new GenreId(novelJpaEntity.getGenreId())),
            fromJpaEntity(novelJpaEntity.getSerialInfoJpaEntity()),
            new Synopsis(novelJpaEntity.getSynopsis()),
            AuthorInfo.init(new AuthorId(novelJpaEntity.getMemberId())));
    }

    private SerialInfo fromJpaEntity(SerialInfoJpaEntity serialInfoJpaEntity) {
        return SerialInfo.init(serialInfoJpaEntity.getType().getDescription(), serialInfoJpaEntity.getInfo());
    }

    private TagJpaEntity mapToTagEntity(Tag tag) {
        return new TagJpaEntity(tag.getName());
    }

    private NovelJpaEntity initNovelJpaEntity(
            final Novel novel,
            final String authorNickName,
            final SerialInfoJpaEntity serialInfoJpaEntity,
            final Long genreId) {

        return new NovelJpaEntity(
                novel.getMetaInfo().getTitle(),
                novel.getMetaInfo().getCoverImageUrl(),
                authorNickName,
                serialInfoJpaEntity,
                novel.getSynopsis().getValue(),
                genreId,
                novel.getAuthorInfo().getAuthorId().getValue());
    }
}
