package com.web.novel.novel.recentread;

import lombok.Value;

public class RecentReadNovel {

    private final RecentReadNovelId recentReadNovelId;

    public RecentReadNovel(
            final RecentReadNovelId recentReadNovelId) {
        this.recentReadNovelId = recentReadNovelId;
    }

    public RecentReadNovelId getRecentReadNovelId() {
        return recentReadNovelId;
    }

    @Value
    public static class RecentReadNovelId {
        Long value;
    }
}
