package com.web.novel.novel.port.in;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.Genre;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.chapter.Chapter;
import java.util.List;
import lombok.Value;

public interface QueryNovelDetailByIdUseCase {

    Result getNovelDetailById(Query query);

    @Value
    class Query {
        NovelId novelId;
        Integer page;
    }
    // todo 최근 본 회차 추가하기
    @Value
    class Result {
        MetaInfo novelMetaInfo;
        Genre genre;
        SerialInfo serialInfo; //연재일 정보
        Synopsis synopsis;
        AuthorInfo authorInfo;
        List<Tag> tags;
        List<Chapter> chapters;
    }

}
