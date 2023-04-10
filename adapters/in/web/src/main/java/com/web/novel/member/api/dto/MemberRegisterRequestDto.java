package com.web.novel.member.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRegisterRequestDto {

    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "nick name is required")
    private String nickName;
}
