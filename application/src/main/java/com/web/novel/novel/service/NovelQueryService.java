package com.web.novel.novel.service;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.port.in.NovelQueryUseCase;
import com.web.novel.novel.port.out.NovelLoadPort;
import com.web.novel.novel.port.out.ViewHistorySavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NovelQueryService implements NovelQueryUseCase {

    private final NovelLoadPort novelLoadPort;
    private final ChapterLoadPort chapterLoadPort;
    private final ViewHistorySavePort novelViewCountUpdatePort;

    public NovelQueryService(
            final NovelLoadPort novelLoadPort,
            final ChapterLoadPort chapterLoadPort,
            final ViewHistorySavePort novelViewCountUpdatePort) {
        this.novelLoadPort = novelLoadPort;
        this.chapterLoadPort = chapterLoadPort;
        this.novelViewCountUpdatePort = novelViewCountUpdatePort;
    }

    @Override
    public Result getNovelDetailPageInfo(final Query query) {
        final MemberId memberId = query.getMemberId();
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
