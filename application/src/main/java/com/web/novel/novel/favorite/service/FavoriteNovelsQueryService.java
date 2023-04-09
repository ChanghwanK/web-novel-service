package com.web.novel.novel.favorite.service;

import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.favorite.port.in.FavoriteNovelsQueryUseCase;
import com.web.novel.novel.favorite.port.out.FavoriteLoadPort;
import com.web.novel.novel.favorite.port.out.NovelCacheLoadPort;
import com.web.novel.novel.favorite.port.out.NovelCacheSavePort;
import com.web.novel.novel.port.out.NovelLoadPort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FavoriteNovelsQueryService implements FavoriteNovelsQueryUseCase {

    private final NovelLoadPort novelLoadPort;
    private final FavoriteLoadPort favoriteLoadPort;
    private final ChapterLoadPort chapterLoadPort;
    private final NovelCacheLoadPort novelCacheLoadPort;
    private final NovelCacheSavePort novelCacheSavePort;

    public FavoriteNovelsQueryService(
            final NovelLoadPort novelLoadPort,
            final FavoriteLoadPort favoriteLoadPort,
            final ChapterLoadPort chapterLoadPort,
            final NovelCacheLoadPort novelCacheLoadPort,
            final NovelCacheSavePort novelCacheSavePort) {
        this.novelLoadPort = novelLoadPort;
        this.favoriteLoadPort = favoriteLoadPort;
        this.chapterLoadPort = chapterLoadPort;
        this.novelCacheLoadPort = novelCacheLoadPort;
        this.novelCacheSavePort = novelCacheSavePort;
    }

    @Override
    public List<Result> query(Query query) {
        var favorites = favoriteLoadPort.getAllByMemberId(query.getMemberId());
        // todo: Factory??
        return favorites.stream()
            .map(favorite -> {
                var key = favorite.getNovelId().getValue();
                return novelCacheLoadPort.getNovelInfoByKey(String.valueOf(key))
                    .map(novelMetaInfoForRead ->
                        new Result(
                            novelMetaInfoForRead.getNovelTitle(),
                            novelMetaInfoForRead.getLastUploadedChapterTitle(),
                            novelMetaInfoForRead.getLastUploadedAt()))
                    .orElseGet(() -> {
                        var novel = novelLoadPort.getById(favorite.getNovelId().getValue());
                        var chapter = chapterLoadPort.getLastUploadedChapterByNovelId(novel.getNovelId());
                        novelCacheSavePort.setCache(novel,chapter);
                        return new Result(novel.getMetaInfo().getTitle(), chapter.getTitle().getValue(), chapter.getLastUpdatedAt());
                    });
            }).collect(Collectors.toList());
    }
}
