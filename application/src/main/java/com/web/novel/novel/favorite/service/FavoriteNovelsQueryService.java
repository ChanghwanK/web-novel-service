package com.web.novel.novel.favorite.service;

import com.web.novel.novel.favorite.port.in.FavoriteNovelsQueryUseCase;
import com.web.novel.novel.favorite.port.out.FavoriteLoadPort;
import com.web.novel.novel.port.out.NovelLoadPort;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FavoriteNovelsQueryService implements FavoriteNovelsQueryUseCase {

    private final NovelLoadPort novelLoadPort;
    private final FavoriteLoadPort favoriteLoadPort;

    public FavoriteNovelsQueryService(
            final NovelLoadPort novelLoadPort,
            final FavoriteLoadPort favoriteLoadPort) {
        this.novelLoadPort = novelLoadPort;

        this.favoriteLoadPort = favoriteLoadPort;
    }

    /**
     * 작품 타이틀, 챕터 타이틀, 몇화
     */
    @Override
    public List<Result> query(Query query) {
        // todo implementation
        return List.of();
    }
}
