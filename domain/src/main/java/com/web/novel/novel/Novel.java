package com.web.novel.novel;

import com.web.novel.exception.TagSizeInvalidException;
import com.web.novel.novel.chapter.Chapter;
import java.util.ArrayList;
import java.util.List;
import lombok.Value;

/**
 * POJO 하게 작성한 이융
 * - Domain은 비즈니스 핵심로직이라고 볼 수 있기 때문에 애플리케이션의 유연성과 이식성이 높아야 한다고 생각합니다.
 * - POJO로 작성할 경우 특정 프레임워크나 플랫폼에 종속되지 않으므로 다양한 환경에서 사용될 수 있기 때문에 POJO로 작성했습니다.
 *
 * 도메인을 속성들을 클래스로 한 이유
 * - 도메인 클래스 부피가 너무 커지는 것을 방지
 * - 풍부한 도메인을 선호하기 때문에 도메인 로직이 하나의 클래스에 집중될 경우 부피가 너무 커진다는 문제가 있음
 * - 이는 곧 가독성 저하되고 원하는 부분을 찾기 어려워지며 유지보수성이 떨어진다고 볼 수 있음
 */
public class Novel {

    private static final int MIN_TAG_SIZE = 3;
    private static final int MAX_TAG_SIZE = 10;

    private final NovelId novelId;
    private final NovelMetaInfo metaInfo;
    private final SerialInfo serialInfo; // 연재 정보
    private final Genre genre;
    private final Synopsis synopsis;
    private final AuthorInfo authorInfo;
    private final List<Tag> tags = new ArrayList<>();
    private final List<Chapter> chapters = new ArrayList<>();

    public Novel(
            final NovelMetaInfo metaInfo,
            final Genre genre,
            final SerialInfo serialInfo,
            final Synopsis synopsis,
            final AuthorInfo authorInfo) {
        this.novelId = null;
        this.metaInfo = metaInfo;
        this.genre = genre;
        this.serialInfo = serialInfo;
        this.synopsis = synopsis;
        this.authorInfo = authorInfo;
    }

    public Novel(
            final NovelId novelId,
            final NovelMetaInfo metaInfo,
            final Genre genre,
            final SerialInfo serialInfo,
            final Synopsis synopsis,
            final AuthorInfo authorInfo) {
        this.novelId = novelId;
        this.metaInfo = metaInfo;
        this.genre = genre;
        this.synopsis = synopsis;
        this.serialInfo =  serialInfo;
        this.authorInfo = authorInfo;
    }

    public NovelMetaInfo getMetaInfo() { return metaInfo; }

    public Genre getGenre() { return genre; }

    public List<Chapter> getChapters() { return chapters; }

    public Synopsis getSynopsis() { return synopsis;}

    public NovelId getNovelId() {
        return novelId;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public void addAllChapter(List<Chapter> chapters) {
        this.chapters.addAll(chapters);
    }

    public SerialInfo getSerialInfo() {
        return serialInfo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTags(List<Tag> tags) {
        if(! (tags.size() == 0)) {
            checkTagSize();
            this.tags.addAll(tags);
        }
    }

    /**
     * 필수가 아니기 때문에 없다면 스킵, 만약 태그가 있다면  최소 3 최대 10개까지 규칙에 맞는지 검증
     */
    private void checkTagSize() {
        var tageSize = tags.size();

        if(tageSize == 0)
            return;

        if(tageSize < MIN_TAG_SIZE || tageSize > MAX_TAG_SIZE)
            throw new TagSizeInvalidException(tageSize);
    }

    public static Novel initNovel(
            final NovelMetaInfo novelMetaInfo,
            final Genre genre,
            final SerialInfo serialInfo,
            final Synopsis synopsis,
            final AuthorInfo authorInfo) {
        return new Novel(novelMetaInfo, genre, serialInfo, synopsis, authorInfo);
    }

    public static Novel initNovelWithId(
            final NovelId novelId,
            final NovelMetaInfo novelMetaInfo,
            final Genre genre,
            final SerialInfo serialInfo,
            final Synopsis synopsis,
            final AuthorInfo authorInfo) {
        return new Novel(novelId, novelMetaInfo, genre, serialInfo, synopsis, authorInfo);
    }

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    @Value
    public static class NovelId {
        Long value;
    }
}
