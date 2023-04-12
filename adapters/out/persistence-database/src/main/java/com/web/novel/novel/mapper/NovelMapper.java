package com.web.novel.novel.mapper;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.Genre;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.Novel;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.NovelMetaInfo;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.entity.GenreJpaEntity;
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

    public Novel mapToDomainWithGenre(final NovelJpaEntity novelJpaEntity, final GenreJpaEntity genreJpaEntity) {
        var novel = new Novel(
            new NovelId(novelJpaEntity.getId()),
            new NovelMetaInfo(novelJpaEntity.getTitle(), novelJpaEntity.getCoverImageUrl()),
            new Genre(new GenreId(genreJpaEntity.getId()), genreJpaEntity.getName()),
            mapToSerialInfoFromJpaEntity(novelJpaEntity.getSerialInfoJpaEntity()),
            new Synopsis(novelJpaEntity.getSynopsis()),
            new AuthorInfo(novelJpaEntity.getAuthorNickName()));

        var tags = novelJpaEntity.getTags().stream()
            .map(this::mapToTagDomain)
            .collect(Collectors.toList());

        novel.addTags(tags);
        return novel;
    }

    private SerialInfo mapToSerialInfoFromJpaEntity(SerialInfoJpaEntity serialInfoJpaEntity) {
        return SerialInfo.init(
            serialInfoJpaEntity.getType().getDescription(),
            serialInfoJpaEntity.getInfo());
    }

    private Tag mapToTagDomain(final TagJpaEntity tagJpaEntity) {
        return new Tag(tagJpaEntity.getName());
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
