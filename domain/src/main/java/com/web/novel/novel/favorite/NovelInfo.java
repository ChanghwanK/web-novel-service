package com.web.novel.novel.favorite;

import lombok.Value;

@Value
public class NovelInfo {
    String novelTitle;
    String chapterTitle;
    Integer chapterOrdering; // 작품 회차
}
