package com.web.novel.novel.service;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.Genre;
import com.web.novel.novel.Novel;
import com.web.novel.novel.port.in.NovelRegisterUseCase;
import com.web.novel.novel.port.out.NovelRegisterPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NovelRegisterService implements NovelRegisterUseCase {

    private final NovelRegisterPort novelRegisterPort;

    public NovelRegisterService(final NovelRegisterPort novelRegisterPort) {
        this.novelRegisterPort = novelRegisterPort;
    }

    @Override
    public void command(Command command) {
        var synopsis = command.getSynopsis();
        synopsis.checkWordSize();

        var initNovel = Novel.initNovel(
            command.getNovelMetaInfo(),
            Genre.init(command.getGenreId()),
            command.getSerialInfo(),
            synopsis,
            AuthorInfo.init(command.getAuthorId()),
            command.getPriceInfo());

        initNovel.addTags(command.getTags());

        novelRegisterPort.store(initNovel);
    }
}
