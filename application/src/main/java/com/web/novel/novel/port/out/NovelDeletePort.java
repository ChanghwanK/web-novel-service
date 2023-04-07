package com.web.novel.novel.port.out;

import com.web.novel.novel.Novel.NovelId;

public interface NovelDeletePort {
    void deleteById(NovelId novelId);
}
