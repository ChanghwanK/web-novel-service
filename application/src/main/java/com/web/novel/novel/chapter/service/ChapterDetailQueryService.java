package com.web.novel.novel.chapter.service;

import com.web.novel.novel.chapter.port.in.ChapterDetailQueryUseCase;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.port.out.NovelExistsCheckPort;
import com.web.novel.novel.port.out.ViewHistorySavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChapterDetailQueryService implements ChapterDetailQueryUseCase {

    private final ViewHistorySavePort viewHistorySavePort;
    private final NovelExistsCheckPort novelExistsCheckPort;
    private final ChapterLoadPort chapterLoadPort;

    public ChapterDetailQueryService(
            final ViewHistorySavePort viewHistorySavePort,
            final NovelExistsCheckPort novelExistsCheckPort,
            final ChapterLoadPort chapterLoadPort) {
        this.viewHistorySavePort = viewHistorySavePort;
        this.novelExistsCheckPort = novelExistsCheckPort;
        this.chapterLoadPort = chapterLoadPort;
    }

    @Override
    public Result getByNovelIdAndChapterId(Query query) {
        var novelId = query.getNovelId().getValue();
        novelExistsCheckPort.checkExistsNovelId(novelId);

        var chapter = chapterLoadPort.getByChapterId(query.getChapterId().getValue());

        viewHistorySavePort.up( query.getMemberId(), query.getNovelId());
        return new Result(chapter);
    }
}
