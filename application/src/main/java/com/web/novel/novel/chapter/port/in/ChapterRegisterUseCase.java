package com.web.novel.novel.chapter.port.in;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.AuthorTalk;
import com.web.novel.novel.chapter.ChapterContent;
import com.web.novel.novel.chapter.ChapterTitle;
import lombok.Value;

public interface ChapterRegisterUseCase {

    void command(Command command);

    @Value
    class Command {
        NovelId novelId;
        ChapterTitle title;
        ChapterContent content;
        AuthorTalk authorTalk;
    }
}
