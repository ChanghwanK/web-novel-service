package com.web.novel.novel.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.novel.api.dto.NovelRegisterRequestDto;
import com.web.novel.novel.api.mapper.NovelApiMapper;
import com.web.novel.novel.port.in.NovelRegisterUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NovelApiController {

    private final NovelRegisterUseCase novelRegisterUseCase;
    private final NovelApiMapper novelApiMapper;

    public NovelApiController(
            final NovelRegisterUseCase novelRegisterUseCase,
            final NovelApiMapper novelApiMapper) {
        this.novelRegisterUseCase = novelRegisterUseCase;
        this.novelApiMapper = novelApiMapper;
    }

    @PostMapping("/api/v1/novel")
    public CommonResponse<Void> registerNovel(
            @RequestBody @Valid NovelRegisterRequestDto dto) {
        novelRegisterUseCase.command(novelApiMapper.toCommand(dto));
        return CommonResponse.DEFAULT;
    }
}
