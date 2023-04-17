package com.web.novel.novel.chapter.port.in;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.chapter.Chapter.ChapterId;
import lombok.Value;

public interface ChapterDetailQueryUseCase {

    Result getByNovelIdAndChapterId(Query query);

    @Value
    class Query {
        MemberId memberId;
        NovelId novelId;
        ChapterId chapterId;
    }

    @Value
    class Result {
        Chapter chapter;
    }
}
