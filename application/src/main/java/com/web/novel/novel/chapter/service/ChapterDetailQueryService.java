package com.web.novel.novel.chapter.service;

import com.web.novel.novel.chapter.port.in.ChapterDetailQueryUseCase;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.port.out.AwsSQSViewCountUpMessageSendPort;
import com.web.novel.novel.port.out.NovelExistsCheckPort;
import com.web.novel.novel.vo.ViewCountUpMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChapterDetailQueryService implements ChapterDetailQueryUseCase {

    private final AwsSQSViewCountUpMessageSendPort sqsViewCountUpMessageSendPort;
    private final NovelExistsCheckPort novelExistsCheckPort;
    private final ChapterLoadPort chapterLoadPort;

    public ChapterDetailQueryService(
            final AwsSQSViewCountUpMessageSendPort sqsViewCountUpMessageSendPort,
            final NovelExistsCheckPort novelExistsCheckPort,
            final ChapterLoadPort chapterLoadPort) {
        this.sqsViewCountUpMessageSendPort = sqsViewCountUpMessageSendPort;
        this.novelExistsCheckPort = novelExistsCheckPort;
        this.chapterLoadPort = chapterLoadPort;
    }

    @Override
    public Result getByNovelIdAndChapterId(Query query) {
        var novelId = query.getNovelId().getValue();
        novelExistsCheckPort.checkExistsNovelId(novelId);

        var chapter = chapterLoadPort.getByChapterId(query.getChapterId().getValue());

        sqsViewCountUpMessageSendPort.sendViewCountUpMessageToSQS(new ViewCountUpMessage(novelId));
        return new Result(chapter);
    }
}
