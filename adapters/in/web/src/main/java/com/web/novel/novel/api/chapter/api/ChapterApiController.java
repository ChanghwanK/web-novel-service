package com.web.novel.novel.api.chapter.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.novel.api.chapter.api.dto.ChapterRegisterRequestDto;
import com.web.novel.novel.api.chapter.api.mapper.ChapterApiMapper;
import com.web.novel.novel.chapter.port.in.ChapterRegisterUseCase;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChapterApiController {

    private final ChapterApiMapper chapterApiMapper;
    private final ChapterRegisterUseCase chapterRegisterUseCase;

    public ChapterApiController(
            final ChapterApiMapper chapterApiMapper,
            final ChapterRegisterUseCase chapterRegisterUseCase) {
        this.chapterApiMapper = chapterApiMapper;
        this.chapterRegisterUseCase = chapterRegisterUseCase;
    }

    @PostMapping("/api/v1/novel/{novelId}/chapter")
    public CommonResponse<Void> registerNewChapter(
            @PathVariable Long novelId,
            @RequestBody @Valid ChapterRegisterRequestDto dto) {

        chapterRegisterUseCase.command(chapterApiMapper.mapToRegisterCommand(novelId, dto));
        return CommonResponse.SUCCESS_DEFAULT;
    }
}
