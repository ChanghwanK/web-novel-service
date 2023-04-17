package com.web.novel.novel.mapper;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.Genre;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.Novel;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.entity.GenreJpaEntity;
import com.web.novel.novel.entity.NovelJpaEntity;
import com.web.novel.novel.entity.PriceInfoEntity;
import com.web.novel.novel.entity.SerialInfo;
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

        var serialInfoJpaEntity = SerialInfo.init(novel.getSerialInfo());
        var initNovelJpaEntity = initNovelJpaEntity(novel, authorNickName, serialInfoJpaEntity, genreId);
        initNovelJpaEntity.addTagEntities(tagJpaEntities);

        return initNovelJpaEntity;
    }

    public Novel mapToDomain(final NovelJpaEntity novelJpaEntity, final GenreJpaEntity genreJpaEntity) {
        var novel = new Novel(
            new NovelId(novelJpaEntity.getId()),
            new MetaInfo(novelJpaEntity.getTitle(), novelJpaEntity.getCoverImageUrl()),
            new Genre(new GenreId(genreJpaEntity.getId()), genreJpaEntity.getName()),
            mapToSerialInfoFromJpaEntity(novelJpaEntity.getSerialInfoJpaEntity()),
            new Synopsis(novelJpaEntity.getSynopsis()),
            new AuthorInfo(novelJpaEntity.getAuthorNickName()),
            ChapterPriceInfo.create(
                novelJpaEntity.getPriceInfo().getPolicy(), novelJpaEntity.getPriceInfo().getPrice()));

        var tags = novelJpaEntity.getTags().stream()
            .map(this::mapToTagDomain)
            .collect(Collectors.toList());

        novel.addTags(tags);
        return novel;
    }

    private com.web.novel.novel.SerialInfo mapToSerialInfoFromJpaEntity(
        SerialInfo serialInfoJpaEntity) {
        return com.web.novel.novel.SerialInfo.create(
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
            final SerialInfo serialInfoJpaEntity,
            final Long genreId) {

        var priceInfo = novel.getChapterPriceInfo();

        return new NovelJpaEntity(
                novel.getMetaInfo().getTitle(),
                novel.getMetaInfo().getCoverImageUrl(),
                authorNickName,
                serialInfoJpaEntity,
                novel.getSynopsis().getValue(),
                PriceInfoEntity.valueOf(priceInfo),
                genreId,
                novel.getAuthorInfo().getAuthorId().getValue());
    }
}
