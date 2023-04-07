package com.web.novel.novel;

import lombok.Value;

/**
 * 웹소설 메타 정보는 다음과 같습니다.
 * 1. 제목
 * 2.
 */
@Value
public class NovelMetaInfo {
    String title;
    String coverImageUrl;
}
