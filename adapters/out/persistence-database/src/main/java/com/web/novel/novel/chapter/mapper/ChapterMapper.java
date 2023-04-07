package com.web.novel.novel.chapter.mapper;

import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapper {

    public ChapterJpaEntity mapToJpaEntity(final Chapter chapter, Integer ordering) {
        return new ChapterJpaEntity(
            null,
            chapter.getTitle().getValue(),
            chapter.getContent().getValue(),
            chapter.getAuthorTalk().getValue(),
            ordering,
            chapter.getNovelId().getValue());
    }
}
