package com.web.novel.novel.favorite;

import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import lombok.Value;

public class Favorite {

    private FavoriteId favoriteId;
    private final MemberId memberId;
    private final NovelId novelId;
    private NovelInfo novelInfo;

    public Favorite(
            final MemberId memberId,
            final NovelId novelId) {
        this.memberId = memberId;
        this.novelId = novelId;
    }

    public Favorite(
            FavoriteId favoriteId,
            MemberId memberId,
            NovelId novelId) {
        this.favoriteId = favoriteId;
        this.memberId = memberId;
        this.novelId = novelId;
    }

    public static Favorite init(MemberId memberId, NovelId novelId) {
        return new Favorite(memberId, novelId);
    }

    public MemberId getMemberId() {return memberId;}

    public NovelId getNovelId() {return novelId;}

    public FavoriteId getFavoriteId() {return favoriteId;}

    public NovelInfo getNovelInfo() {return novelInfo;}

    @Value
    public static class FavoriteId {
        Long value;
    }
}
