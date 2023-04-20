package com.web.novel.novel;

import com.web.novel.exception.SynopsisWordSizeOverException;
import lombok.Value;

@Value
public class Synopsis {
    String value;

    public Synopsis(final String value) {
        checkWordSize(value);
        this.value = value;
    }
    private void checkWordSize(final String value) {
        if(value.length() > 2000)
            throw new SynopsisWordSizeOverException(value.length());
    }
}
