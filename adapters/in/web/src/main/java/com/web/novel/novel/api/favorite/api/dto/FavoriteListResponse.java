package com.web.novel.novel.api.favorite.api.dto;

import lombok.Getter;

@Getter
public class FavoriteListResponse {
    private final String novelTitle;
    private final String chapterTitle;
    private final String uploadedAt;

    public FavoriteListResponse(
            final String novelTitle,
            final String chapterTitle,
            final String uploadedAt) {
        this.novelTitle = novelTitle;
        this.chapterTitle = chapterTitle;
        this.uploadedAt = uploadedAt;
    }
}
