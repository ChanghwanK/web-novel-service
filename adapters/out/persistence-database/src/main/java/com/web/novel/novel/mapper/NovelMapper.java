package com.web.novel.novel.mapper;

import com.web.novel.novel.Novel;
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
