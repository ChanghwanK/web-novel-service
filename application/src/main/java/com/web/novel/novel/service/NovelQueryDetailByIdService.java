package com.web.novel.novel.service;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.port.in.QueryNovelDetailByIdUseCase;
import com.web.novel.novel.port.out.NovelLoadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NovelQueryDetailByIdService implements QueryNovelDetailByIdUseCase {

    private final NovelLoadPort novelLoadPort;
    private final ChapterLoadPort chapterLoadPort;

    public NovelQueryDetailByIdService(
            final NovelLoadPort novelLoadPort,
            final ChapterLoadPort chapterLoadPort) {
        this.novelLoadPort = novelLoadPort;
        this.chapterLoadPort = chapterLoadPort;
    }

    @Override
    public Result getNovelDetailById(final Query query) {
        final NovelId novelId = query.getNovelId();
        // 작품 정보
        var novel = novelLoadPort.getById(novelId.getValue());
        // 회자 정보 10개
        var chapters = chapterLoadPort.getChapterOrderByUploadedAtDESCWithPage(novelId, query.getPage());
        return new Result(
            novel.getMetaInfo(),
            novel.getGenre(),
            novel.getSerialInfo(),
            novel.getSynopsis(),
            novel.getAuthorInfo(),
            novel.getTags(),
            chapters);
    }
}
