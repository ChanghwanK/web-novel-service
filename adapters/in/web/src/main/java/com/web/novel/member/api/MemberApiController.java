package com.web.novel.member.api;

import com.web.novel.config.response.CommonResponse;
import com.web.novel.member.api.dto.MemberRegisterRequestDto;
import com.web.novel.member.api.mapper.MemberRequestMapper;
import com.web.novel.member.port.in.MemberRegisterUseCase;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MemberApiController {

    private final MemberRequestMapper requestMapper;
    private final MemberRegisterUseCase memberRegisterUseCase;

    public MemberApiController(
            final MemberRequestMapper requestMapper,
            final MemberRegisterUseCase memberRegisterUseCase) {
        this.requestMapper = requestMapper;
        this.memberRegisterUseCase = memberRegisterUseCase;
    }

    @PostMapping("/api/v1/member")
    public CommonResponse<Void> registerNewMember(
            @RequestBody @Valid MemberRegisterRequestDto dto) {
        var command = requestMapper.toCommand(dto);
        memberRegisterUseCase.command(command);
        return CommonResponse.DEFAULT;
    }
}
