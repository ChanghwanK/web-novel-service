package com.web.novel.novel.api.chapter.api.mapper;

import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.api.chapter.api.dto.ChapterDetailResponse;
import com.web.novel.novel.api.chapter.api.dto.ChapterRegisterRequestDto;
import com.web.novel.novel.chapter.AuthorTalk;
import com.web.novel.novel.chapter.ChapterContent;
import com.web.novel.novel.chapter.ChapterTitle;
import com.web.novel.novel.chapter.port.in.ChapterDetailQueryUseCase;
import com.web.novel.novel.chapter.port.in.ChapterRegisterUseCase;
import org.springframework.stereotype.Component;

@Component
public class ChapterApiMapper {
    public ChapterRegisterUseCase.Command mapToRegisterCommand(final Long novelId, final ChapterRegisterRequestDto dto) {
        return new ChapterRegisterUseCase.Command(
            new NovelId(novelId),
            new ChapterTitle(dto.getTitle()),
            new ChapterContent(dto.getContent()),
            new AuthorTalk(dto.getAuthorTalk())
        );
    }

    public ChapterDetailResponse mapToQueryResponse(ChapterDetailQueryUseCase.Result result) {
        final var chapter = result.getChapter();
        return new ChapterDetailResponse(
            chapter.getOrdering().getValue(),
            chapter.getTitle().getValue(),
            chapter.getContent().getValue(),
            chapter.getAuthorTalk().getValue(),
            chapter.getLastUpdatedAt()
        );
    }
}
