package com.web.novel.novel.api.favorite.api.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FavoriteRegisterRequestDto {
    private Long memberId; // 회원가입을 헀다면 Authorization으로 받을 수 있지만 현재는 DTO로 받는 것으로 진행

}
