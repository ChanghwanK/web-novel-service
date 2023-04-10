package com.web.novel.novel.favorite.port.out;

import com.web.novel.novel.Novel;
import com.web.novel.novel.chapter.Chapter;

public interface NovelCacheSavePort {
    void setCache(Novel novel, Chapter chapter);

}
