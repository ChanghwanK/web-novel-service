package com.web.novel.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse <T> {
    public static final CommonResponse<Void> DEFAULT = new CommonResponse<>(Result.SUCCESS, null);

    private Result result;
    private T data;

    enum Result {
        SUCCESS, FAIL
    }

    public static <T> CommonResponse<T> success(T data) {
        return (CommonResponse<T>) CommonResponse.builder()
            .result(Result.SUCCESS)
            .data(data)
            .build();
    }
}
