package com.web.novel.novel.chapter;

import com.web.novel.novel.Novel.NovelId;
import lombok.Value;

public class Chapter {

    public static final Integer FIRST_CHAPTER = 1;

    private ChapterId chapterId;
    private final ChapterTitle title;
    private final ChapterContent content;
    private final AuthorTalk authorTalk; // 작가의 말
    private Ordering ordering;
    private final NovelId novelId;
    private String lastUpdatedAt;

    public Chapter(
            final ChapterTitle title,
            final ChapterContent content,
            final AuthorTalk authorTalk,
            final NovelId novelId) {
        this.title = title;
        this.content = content;
        this.authorTalk = authorTalk;
        this.novelId = novelId;
    }

    public Chapter(
            final ChapterId chapterId,
            final ChapterTitle title,
            final ChapterContent content,
            final AuthorTalk authorTalk,
            final NovelId novelId,
            final Ordering ordering,
            final String lastUpdatedAt) {
        this.chapterId = chapterId;
        this.title = title;
        this.content = content;
        this.authorTalk = authorTalk;
        this.novelId = novelId;
        this.ordering = ordering;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public ChapterTitle getTitle() {
        return title;
    }

    public ChapterContent getContent() {
        return content;
    }

    public AuthorTalk getAuthorTalk() {
        return authorTalk;
    }

    public ChapterId getChapterId() {
        return chapterId;
    }

    public NovelId getNovelId() {
        return novelId;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @Value
    public static class ChapterId {
        Long value;
    }
}
