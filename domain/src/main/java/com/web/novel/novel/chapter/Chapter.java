package com.web.novel.novel.chapter;

import lombok.Value;

public class Chapter {

    public static final Integer FIRST_CHAPTER = 1;

    private ChapterId chapterId;
    private final ChapterTitle title;
    private final ChapterContent content;
    private final AuthorTalk authorTalk; // 작가의 말
    private Ordering ordering;
    private final NovelId novelId;

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
            final Ordering ordering) {
        this.chapterId = chapterId;
        this.title = title;
        this.content = content;
        this.authorTalk = authorTalk;
        this.novelId = novelId;
        this.ordering = ordering;
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

    @Value
    public static class ChapterId {
        Long value;
    }

    @Value
    public static class NovelId {
        Long value;
    }
}
