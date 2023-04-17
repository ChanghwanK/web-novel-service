package com.web.novel.novel.api.mapper;

import com.web.novel.novel.AuthorInfo.AuthorId;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.api.dto.request.NovelRegisterRequestDto;
import com.web.novel.novel.api.dto.request.NovelRegisterRequestDto.TagRegisterRequest;
import com.web.novel.novel.api.dto.response.NovelPageInfoResponse;
import com.web.novel.novel.api.dto.response.NovelPageInfoResponse.ChapterInfo;
import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.port.in.NovelDeleteUseCase;
import com.web.novel.novel.port.in.NovelDeleteUseCase.Command;
import com.web.novel.novel.port.in.NovelQueryUseCase.Result;
import com.web.novel.novel.port.in.NovelRegisterUseCase;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class NovelApiMapper {

    public NovelRegisterUseCase.Command mapToRegisterCommand(final NovelRegisterRequestDto dto) {
        var priceInfoRequest = dto.getPriceInfo();
        var tags = dto.getTags().stream()
            .map(this::mapToTag)
            .collect(Collectors.toList());
        /**
         * 생성 분리 고민
         * - 특정 조건에 따라 Enum 타입이 달라져야함
         * - 무분별하게 If 조건이 퍼지는 것이 별로임
         * - 한 곳에서 정리되면 좋을 것 같음  <- 유지보수성 ++
         * 결론 (팩토리 패선이 좋은 이유)
         * - 객체 생성 책임을 지는 메서드를 만들고 사용하자
         */
        return new NovelRegisterUseCase.Command(
            new MetaInfo(dto.getTitle(), dto.getCoverImageUrl()),
            ChapterPriceInfo.create(priceInfoRequest.getPolicy(), priceInfoRequest.getPrice()),
            SerialInfo.create(dto.getSerialInfo().getType(), dto.getSerialInfo().getInfo()),
            new GenreId(dto.getGenreId()),
            new Synopsis(dto.getSynopsis()),
            tags,
            new AuthorId(dto.getAuthorId()));
    }

    public NovelDeleteUseCase.Command mapToDeleteCommand(final Long novelId) {
        return new Command(new NovelId(novelId));
    }

    private Tag mapToTag(TagRegisterRequest request) {
        return new Tag(request.getName());
    }

    public NovelPageInfoResponse mapToNovelPageInfoResponse(final Result result) {
        var chapters = result.getChapters().stream()
            .map(this::mapToChapterInfoResponse)
            .collect(Collectors.toList());

        var tags = result.getTags().stream().map(Tag::getName)
            .collect(Collectors.toList());

        return new NovelPageInfoResponse(
            result.getNovelMetaInfo().getTitle(),
            result.getNovelMetaInfo().getCoverImageUrl(),
            result.getSynopsis().getValue(),
            result.getGenre().getName(),
            result.getAuthorInfo().getAuthorNickName(),
            tags,
            chapters);
    }

    private ChapterInfo mapToChapterInfoResponse(final Chapter chapter) {
        return new ChapterInfo(
            chapter.getChapterId().getValue(),
            chapter.getOrdering().getValue(),
            chapter.getTitle().getValue(),
            chapter.getLastUpdatedAt());
    }
}
