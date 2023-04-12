package com.web.novel.novel.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.api.dto.request.NovelRegisterRequestDto;
import com.web.novel.novel.api.mapper.NovelApiMapper;
import com.web.novel.novel.port.in.NovelDeleteUseCase;
import com.web.novel.novel.port.in.NovelQueryUseCase;
import com.web.novel.novel.port.in.NovelQueryUseCase.Query;
import com.web.novel.novel.port.in.NovelRegisterUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NovelApiController {

    private final NovelRegisterUseCase novelRegisterUseCase;
    private final NovelQueryUseCase novelQueryUseCase;
    private final NovelDeleteUseCase novelDeleteUseCase;
    private final NovelApiMapper novelApiMapper;

    public NovelApiController(
            final NovelRegisterUseCase novelRegisterUseCase,
            final NovelQueryUseCase novelQueryUseCase,
            final NovelDeleteUseCase novelDeleteUseCase,
            final NovelApiMapper novelApiMapper) {
        this.novelRegisterUseCase = novelRegisterUseCase;
        this.novelQueryUseCase = novelQueryUseCase;
        this.novelDeleteUseCase = novelDeleteUseCase;
        this.novelApiMapper = novelApiMapper;
    }

    @PostMapping("/api/v1/novel")
    public CommonResponse<Void> registerNovel(
            @RequestBody @Valid NovelRegisterRequestDto dto) {
        novelRegisterUseCase.command(novelApiMapper.mapToRegisterCommand(dto));
        return CommonResponse.SUCCESS_DEFAULT;
    }

    @GetMapping("/api/v1/novels/{novelId}")
    public CommonResponse<?> getNovelForNovelPage (
            @PathVariable Long novelId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "memberId") Long memberId) {
        var res = novelQueryUseCase.getNovelDetailPageInfo(
            new Query(
                new NovelId(novelId),
                new MemberId(memberId),
                page));
        return CommonResponse.success(novelApiMapper.mapToNovelPageInfoResponse(res));
    }

    @DeleteMapping("/api/v1/novel/{novelId}")
    public CommonResponse<Void> removeNovel(
            @PathVariable Long novelId) {
        novelDeleteUseCase.command(novelApiMapper.mapToDeleteCommand(novelId));
        return CommonResponse.SUCCESS_DEFAULT;
    }
}
