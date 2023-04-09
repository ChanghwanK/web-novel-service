package com.web.novel.novel.vo;

import lombok.Value;

@Value
public class NovelMetaInfoForRead {

    String novelTitle;
    LastUploadedChapterInfo lastUploadedChapterInfo;

    public String getLastUploadedChapterTitle() {
        return lastUploadedChapterInfo.chapterTitle;
    }

    public String getLastUploadedAt() {
        return lastUploadedChapterInfo.uploadedAt;
    }


    @Value
    public static class LastUploadedChapterInfo {
        String chapterTitle;
        String uploadedAt;
    }
}
