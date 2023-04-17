package com.web.novel.novel.api.chapter.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.api.chapter.api.dto.ChapterDetailResponse;
import com.web.novel.novel.api.chapter.api.dto.ChapterRegisterRequestDto;
import com.web.novel.novel.api.chapter.api.mapper.ChapterApiMapper;
import com.web.novel.novel.chapter.Chapter.ChapterId;
import com.web.novel.novel.chapter.port.in.ChapterDetailQueryUseCase;
import com.web.novel.novel.chapter.port.in.ChapterDetailQueryUseCase.Query;
import com.web.novel.novel.chapter.port.in.ChapterPurchaseUseCase;
import com.web.novel.novel.chapter.port.in.ChapterPurchaseUseCase.Command;
import com.web.novel.novel.chapter.port.in.ChapterRegisterUseCase;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChapterApiController {

    private final ChapterApiMapper chapterApiMapper;
    private final ChapterRegisterUseCase chapterRegisterUseCase;
    private final ChapterDetailQueryUseCase chapterDetailQueryUseCase;
    private final ChapterPurchaseUseCase chapterPurchaseUseCase;

    public ChapterApiController(
            final ChapterApiMapper chapterApiMapper,
            final ChapterRegisterUseCase chapterRegisterUseCase,
            final ChapterDetailQueryUseCase chapterDetailQueryUseCase,
            final ChapterPurchaseUseCase chapterPurchaseUseCase) {
        this.chapterApiMapper = chapterApiMapper;
        this.chapterRegisterUseCase = chapterRegisterUseCase;
        this.chapterDetailQueryUseCase = chapterDetailQueryUseCase;
        this.chapterPurchaseUseCase = chapterPurchaseUseCase;
    }

    @PostMapping("/api/v1/novel/{novelId}/chapter")
    public void registerNewChapter(
            @PathVariable Long novelId,
            @RequestBody @Valid ChapterRegisterRequestDto dto) {

        chapterRegisterUseCase.command(chapterApiMapper.mapToRegisterCommand(novelId, dto));
    }

    @PostMapping("/api/v1/novel/{novelId}/chapter/{chapterId}")
    public void purchaseChapter(
        @PathVariable Long novelId,
        @PathVariable Long chapterId,
        @RequestParam Long memberId) {

        chapterPurchaseUseCase.command(
            new Command(
                new MemberId(memberId),
                new NovelId(novelId),
                new ChapterId(chapterId)));
    }

    @GetMapping("/api/v1/novel/{novelId}/chapter/{chapterId}")
    public CommonResponse<ChapterDetailResponse> getChapterDetailInfo(
            @PathVariable Long novelId,
            @PathVariable Long chapterId,
            @RequestParam(name = "memberId") Long memberId) {
        var res = chapterDetailQueryUseCase.getByNovelIdAndChapterId(
            new Query(
                new MemberId(memberId),
                new NovelId(novelId),
                new ChapterId(chapterId)));

        return CommonResponse.success(chapterApiMapper.mapToQueryResponse(res));
    }
}
