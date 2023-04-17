package com.web.novel.novel.api.dto.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NovelRegisterRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String coverImageUrl;

    @NotNull
    private SerialInfoRegisterRequest serialInfo;

    private PriceInfoRequest priceInfo;

    @Size(min = 0, max = 2000)
    private String synopsis;

    @NotNull
    private Long genreId;

    @NotNull
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
        @NotBlank
        private String type;
        @NotBlank
        private String info;
    }

    @ToString
    @Getter
    public static class PriceInfoRequest {
        @NotNull
        private String policy;
        private int price;
    }
}
