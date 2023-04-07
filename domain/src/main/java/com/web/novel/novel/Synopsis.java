package com.web.novel.novel;

import com.web.novel.exception.SynopsisWordSizeOverException;
import lombok.Value;

@Value
public class Synopsis {
    String value;

    public void checkWordSize() {
        if(value.length() > 2000)
            throw new SynopsisWordSizeOverException(value.length());
    }
}
