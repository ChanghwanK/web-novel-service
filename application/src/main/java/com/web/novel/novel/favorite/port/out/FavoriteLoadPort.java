package com.web.novel.novel.favorite.port.out;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.favorite.Favorite;
import java.util.List;

public interface FavoriteLoadPort {
    Favorite getByMemberIdAndNovelId(MemberId memberId, NovelId novelId);
    List<Favorite> getAllByMemberId(MemberId memberId);
}
