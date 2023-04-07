package com.web.novel.novel.api.chapter.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ChapterRegisterRequestDto {

    @NotBlank(message = "title은 필수 값입니다.")
    private String title;
    @Size(max = 20000, message = "본문은 최대 2만 글자까지 허용됩니다.")
    private String content;
    @Size(max = 150, message = "작가의 말은 최대 150글자까지 하용됩니다.")
    private String authorTalk;
}
