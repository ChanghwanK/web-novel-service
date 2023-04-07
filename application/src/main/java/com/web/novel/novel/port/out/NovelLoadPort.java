package com.web.novel.novel.port.out;

import com.web.novel.novel.Novel;
import com.web.novel.novel.Novel.NovelId;

public interface NovelLoadPort {

    Novel getById(NovelId novelId);
}
