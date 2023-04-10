package com.web.novel.novel.service;

import com.web.novel.novel.port.in.NovelDeleteUseCase;
import com.web.novel.novel.port.out.NovelDeletePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NovelDeleteService implements NovelDeleteUseCase {

    private final NovelDeletePort novelDeletePort;

    public NovelDeleteService(final NovelDeletePort novelDeletePort) {
        this.novelDeletePort = novelDeletePort;
    }

    @Override
    public void command(Command command) {
        novelDeletePort.deleteById(command.getNovelId());
    }
}
