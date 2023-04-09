package com.web.novel.novel.favorite.port.out;

import com.web.novel.novel.vo.NovelMetaInfoForRead;
import java.util.Optional;

public interface NovelCacheLoadPort {
    Optional<NovelMetaInfoForRead> getNovelInfoByKey(String key);
}
