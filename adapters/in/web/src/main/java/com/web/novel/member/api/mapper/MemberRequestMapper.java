package com.web.novel.member.api.mapper;

import com.web.novel.annotations.Mapper;
import com.web.novel.member.Email;
import com.web.novel.member.NickName;
import com.web.novel.member.api.dto.MemberRegisterRequestDto;
import com.web.novel.member.port.in.MemberRegisterUseCase;

@Mapper
public class MemberRequestMapper {

    public MemberRegisterUseCase.Command toCommand(MemberRegisterRequestDto dto) {
        return new MemberRegisterUseCase.Command(
            new Email(dto.getEmail()),
            new NickName(dto.getNickName()));
    }
}
