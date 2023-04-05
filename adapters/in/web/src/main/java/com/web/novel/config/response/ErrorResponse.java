package com.web.novel.config.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final int code;
    private final String message;

    public static final ErrorResponse BAD_REQUEST = toErrorResponseDto(HttpStatus.BAD_REQUEST);
    public static final ErrorResponse FORBIDDEN = toErrorResponseDto(HttpStatus.FORBIDDEN);
    public static final ErrorResponse NOT_FOUND = toErrorResponseDto(HttpStatus.NOT_FOUND);
    public static final ErrorResponse INTERNAL_SERVER_ERROR = toErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR);

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private static ErrorResponse toErrorResponseDto(final HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}
