package com.web.novel.config.aop;

import com.web.novel.config.response.ErrorResponse;
import com.web.novel.exception.BaseException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {
    /**
     *  400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse handleException(ConstraintViolationException e) {
        log.error("", e);
        return ErrorResponse.BAD_REQUEST;
    }

    /**
     *  400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse handleException(BaseException e) {
        log.error("", e);
        return ErrorResponse.BAD_REQUEST;
    }

    /**
     * 500
     * 장애상황
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    ErrorResponse handleException(RuntimeException e) {
        log.error("", e);
        return ErrorResponse.INTERNAL_SERVER_ERROR;
    }
}
