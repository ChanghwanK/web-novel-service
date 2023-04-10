package com.web.novel.novel.chapter;

import com.web.novel.exception.ChapterContentSizeOverException;
import lombok.Value;

@Value
public class ChapterContent {
    static final int CONTENT_MAX_SIZE = 20000;
    String value;

    public void contentSizeValidation() {
        var len = value.length();
        if(len > CONTENT_MAX_SIZE) {
            throw new ChapterContentSizeOverException(len);
        }
    }
}
