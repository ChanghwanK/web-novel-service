package com.web.novel.novel.port.in;

import com.web.novel.novel.AuthorInfo.AuthorId;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Tag;
import com.web.novel.novel.Synopsis;
import java.util.List;
import lombok.Value;

public interface NovelRegisterUseCase {

    void command(Command command);

    @Value
    class Command {
        MetaInfo novelMetaInfo;
        ChapterPriceInfo priceInfo;
        SerialInfo serialInfo;
        GenreId genreId;
        Synopsis synopsis;
        List<Tag> tags;
        AuthorId authorId;
    }
}
