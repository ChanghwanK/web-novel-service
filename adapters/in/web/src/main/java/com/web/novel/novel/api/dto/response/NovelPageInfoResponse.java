package com.web.novel.novel.api.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NovelPageInfoResponse {
    private final String novelTitle;
    private final String coverImageUrl;
    private final String synopsis;
    private final String genreName;
    private final String authorNickName;
    private final List<String> tags;
    private final List<ChapterInfo> chapters;

    public NovelPageInfoResponse(
            final String novelTitle,
            final String coverImageUrl,
            final String synopsis,
            final String genreName,
            final String authorNickName,
            final List<String> tags,
            final List<ChapterInfo> chapters) {
        this.novelTitle = novelTitle;
        this.coverImageUrl = coverImageUrl;
        this.synopsis = synopsis;
        this.genreName = genreName;
        this.authorNickName = authorNickName;
        this.tags = tags;
        this.chapters = chapters;
    }

    @ToString
    @Getter
    public static class ChapterInfo {
        private final Long chapterId;
        private final Integer ordering;
        private final String chapterTitle;
        private final String lastUploadedAt;

        public ChapterInfo(
                final Long chapterId,
                final Integer ordering,
                final String chapterTitle,
                final String lastUploadedAt) {
            this.chapterId = chapterId;
            this.ordering = ordering;
            this.chapterTitle = chapterTitle;
            this.lastUploadedAt = lastUploadedAt;
        }
    }
}
