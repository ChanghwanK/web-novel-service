package com.web.novel;

import com.web.novel.novel.Novel;
import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.favorite.port.out.NovelCacheLoadPort;
import com.web.novel.novel.favorite.port.out.NovelCacheSavePort;
import com.web.novel.novel.vo.NovelMetaInfoForRead;
import com.web.novel.novel.vo.NovelMetaInfoForRead.LastUploadedChapterInfo;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class NovelCacheAdapter implements NovelCacheSavePort, NovelCacheLoadPort {

    private final RedisService redisService;

    private NovelCacheAdapter(final RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public Optional<NovelMetaInfoForRead> getNovelInfoByKey(String key) {
        return redisService.get(key,NovelMetaInfoForRead.class);
    }

    @Override
    public void setCache(Novel novel, Chapter chapter) {
        var key = String.valueOf(novel.getNovelId().getValue());
        var lastUploadedChapterInfo = new LastUploadedChapterInfo(chapter.getTitle().getValue(), chapter.getLastUpdatedAt());
        var data = new NovelMetaInfoForRead(novel.getMetaInfo().getTitle(),lastUploadedChapterInfo);

        redisService.set(key, data);
    }
}
