package com.web.novel.novel.api.chapter.api.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ChapterDetailResponse {

    private final Integer ordering;
    private final String chapterTitle;
    private final String content;
    private final String authorTalk;
    private final String uploadedAt;

    public ChapterDetailResponse(
            final Integer ordering,
            final String chapterTitle,
            final String content,
            final String authorTalk,
            final String uploadedAt) {
        this.ordering = ordering;
        this.chapterTitle = chapterTitle;
        this.content = content;
        this.authorTalk = authorTalk;
        this.uploadedAt = uploadedAt;
    }
}
