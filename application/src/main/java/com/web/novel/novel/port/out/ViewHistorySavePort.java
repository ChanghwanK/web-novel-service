package com.web.novel.novel.port.out;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;

public interface ViewHistorySavePort {
    void up(MemberId memberId, NovelId novelId);
}
