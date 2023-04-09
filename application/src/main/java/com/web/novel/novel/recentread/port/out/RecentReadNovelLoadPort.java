package com.web.novel.novel.recentread.port.out;

public interface RecentReadNovelLoadPort {
    void getByMemberIdAndNovelId(Long memberId, Long novelId);
}
