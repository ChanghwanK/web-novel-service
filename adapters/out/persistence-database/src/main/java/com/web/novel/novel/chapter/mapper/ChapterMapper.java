package com.web.novel.novel.chapter.mapper;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.AuthorTalk;
import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.chapter.Chapter.ChapterId;
import com.web.novel.novel.chapter.ChapterContent;
import com.web.novel.novel.chapter.ChapterTitle;
import com.web.novel.novel.chapter.Ordering;
import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import java.time.format.DateTimeFormatter;
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

    public Chapter mapToDomain(ChapterJpaEntity chapterJpaEntity) {
        return new Chapter(
            new ChapterId(chapterJpaEntity.getId()),
            new ChapterTitle(chapterJpaEntity.getTitle()),
            new ChapterContent(chapterJpaEntity.getContent()),
            new AuthorTalk(chapterJpaEntity.getAuthorTalk()),
            new NovelId(chapterJpaEntity.getNovelId()),
            new Ordering(chapterJpaEntity.getOrdering()),
            chapterJpaEntity.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
