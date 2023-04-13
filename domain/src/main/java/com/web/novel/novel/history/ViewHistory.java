package com.web.novel.novel.history;


public class ViewHistory {
    private final Long memberId;
    private final Long chapterId;
    private final Long novelId;

    public ViewHistory(
            final Long memberId,
            final Long novelId,
            final Long chapterId) {
        this.memberId = memberId;
        this.novelId = novelId;
        this.chapterId = chapterId;

    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public Long getNovelId() {
        return novelId;
    }
}
