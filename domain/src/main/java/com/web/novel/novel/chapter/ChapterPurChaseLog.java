package com.web.novel.novel.chapter;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.Chapter.ChapterId;

public class ChapterPurChaseLog {

    private final MemberId memberId;
    private final NovelId novelId;
    private final ChapterId chapterId;
    private ChapterPriceInfo chapterPriceInfo;

    public ChapterPurChaseLog(
            final MemberId memberId,
            final NovelId novelId,
            final ChapterId chapterId,
            final ChapterPriceInfo chapterPriceInfo) {

        this.memberId = memberId;
        this.novelId = novelId;
        this.chapterId = chapterId;
        this.chapterPriceInfo = chapterPriceInfo;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public NovelId getNovelId() {
        return novelId;
    }

    public ChapterId getChapterId() {
        return chapterId;
    }

    public ChapterPriceInfo getChapterPriceInfo() {
        return chapterPriceInfo;
    }
}
