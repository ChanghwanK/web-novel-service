package com.web.novel.novel.favorite.port;


import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import lombok.Value;

public interface FavoriteUpsertUseCase {
    void command(Command command);

    @Value
    class Command {
        NovelId novelId;
        MemberId memberId;
    }
}
