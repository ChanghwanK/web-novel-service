package com.web.novel.novel.chapter.port.in;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.Chapter.ChapterId;
import lombok.Value;

public interface ChapterPurchaseUseCase {

    void command(Command command);

    @Value
    class Command {
        MemberId memberId;
        NovelId novelId;
        ChapterId chapterId;
    }
}
