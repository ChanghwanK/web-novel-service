package com.web.novel.novel.port.out;

import com.web.novel.novel.Novel;

public interface NovelLoadPort {
    Novel getById(Long novelId);
}
