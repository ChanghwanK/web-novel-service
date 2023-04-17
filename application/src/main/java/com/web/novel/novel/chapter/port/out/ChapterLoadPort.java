package com.web.novel.novel.chapter.port.out;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.Chapter;
import java.util.List;

public interface ChapterLoadPort {
    Chapter getLastUploadedChapterByNovelId(NovelId novelId);
    List<Chapter> getChapterOrderByUploadedAtDESCWithPage(NovelId novelId, Integer page);
    Chapter getByChapterId(Long value);
}
