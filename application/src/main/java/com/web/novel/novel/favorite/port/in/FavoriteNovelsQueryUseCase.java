package com.web.novel.novel.favorite.port.in;

import com.web.novel.member.Member.MemberId;
import java.util.List;
import lombok.Value;

public interface FavoriteNovelsQueryUseCase {

    List<Result> query(Query query);

    @Value
    class Query {
        MemberId memberId;
    }

    @Value
    class Result {
        String novelTitle;
        String lastUploadedChapterTile;
        String lastUploadedAt;
    }

}
