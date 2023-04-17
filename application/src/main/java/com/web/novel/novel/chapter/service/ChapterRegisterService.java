package com.web.novel.novel.chapter.service;

import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.chapter.port.in.ChapterRegisterUseCase;
import com.web.novel.novel.chapter.port.out.ChapterRegisterPort;
import com.web.novel.novel.port.out.NovelLoadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChapterRegisterService implements ChapterRegisterUseCase {

    private final NovelLoadPort novelLoadPort;
    private final ChapterRegisterPort novelChapterRegisterPort;

    public ChapterRegisterService(
            final NovelLoadPort novelLoadPort,
            final ChapterRegisterPort novelChapterRegisterPort) {
        this.novelLoadPort = novelLoadPort;
        this.novelChapterRegisterPort = novelChapterRegisterPort;
    }

    @Override
    public void command(Command command) {
        novelChapterRegisterPort.register(
            new Chapter(
                command.getTitle(),
                command.getContent(),
                command.getAuthorTalk(),
                command.getNovelId()));
    }
}
