package com.web.novel.novel;

import static org.junit.jupiter.api.Assertions.*;

import com.web.novel.exception.SynopsisWordSizeOverException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SynopsisTest {

    @Test
    void 시놉시스_2천_글자_체크() {
        StringBuilder value = new StringBuilder();
        value.append("a".repeat(2_0001));

        assertThrows(
            SynopsisWordSizeOverException.class,
            () -> new Synopsis(value.toString())
        );
    }

}