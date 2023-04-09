package com.web.novel.novel.chapter.port.out;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.Chapter;

public interface ChapterLoadPort {
    Chapter getLastUploadedChapterByNovelId(NovelId novelId);
}
