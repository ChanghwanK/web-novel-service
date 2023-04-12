package com.web.novel.novel.api.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NovelRegisterRequestDto {
    private String title;
    private String coverImageUrl;
    private SerialInfoRegisterRequest serialInfo;
    private String synopsis;
    private Long genreId;
    private Long authorId;
    private List<TagRegisterRequest> tags;

    @ToString
    @Getter
    public static class TagRegisterRequest {
        private String name;
    }

    @ToString
    @Getter
    public static class SerialInfoRegisterRequest {
        private String type;
        private String info;
    }
}
