package com.web.novel.novel.favorite.service;

import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.favorite.Favorite;
import com.web.novel.novel.favorite.port.FavoriteUpsertUseCase;
import com.web.novel.novel.favorite.port.out.FavoriteDeletePort;
import com.web.novel.novel.favorite.port.out.FavoriteLoadPort;
import com.web.novel.novel.favorite.port.out.FavoriteRegisterPort;
import com.web.novel.novel.port.out.NovelLoadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FavoriteRegisterService implements FavoriteUpsertUseCase {

    private final FavoriteRegisterPort favoriteRegisterPort;
    private final FavoriteDeletePort favoriteDeletePort;
    private final FavoriteLoadPort favoriteLoadPort;
    private final NovelLoadPort novelLoadPort;
    private final ChapterLoadPort chapterLoadPort;

    public FavoriteRegisterService(
            final FavoriteRegisterPort favoriteRegisterPort,
            final FavoriteDeletePort favoriteDeletePort,
            final FavoriteLoadPort favoriteLoadPort,
            final NovelLoadPort novelLoadPort,
            final ChapterLoadPort chapterLoadPort) {
        this.favoriteRegisterPort = favoriteRegisterPort;
        this.favoriteDeletePort = favoriteDeletePort;
        this.favoriteLoadPort = favoriteLoadPort;
        this.novelLoadPort = novelLoadPort;
        this.chapterLoadPort = chapterLoadPort;
    }

    @Override
    public void command(Command command) {
        var novelId = command.getNovelId();

        novelLoadPort.getById(novelId.getValue());
        chapterLoadPort.getLastUploadedChapterByNovelId(novelId);

        Favorite favorite = favoriteLoadPort.getByMemberIdAndNovelId(command.getMemberId(), command.getNovelId());

        if(favorite == null)
            favoriteRegisterPort.register(Favorite.newInstance(command.getMemberId(), command.getNovelId()));
        else
            favoriteDeletePort.deleteById(favorite.getFavoriteId());
    }
}
