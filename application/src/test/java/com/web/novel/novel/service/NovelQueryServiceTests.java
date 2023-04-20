package com.web.novel.novel.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.novel.novel.AuthorInfo;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.ChapterPriceInfo.Policy;
import com.web.novel.novel.Genre;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.Novel;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.chapter.AuthorTalk;
import com.web.novel.novel.chapter.Chapter;
import com.web.novel.novel.chapter.Chapter.ChapterId;
import com.web.novel.novel.chapter.ChapterContent;
import com.web.novel.novel.chapter.ChapterTitle;
import com.web.novel.novel.chapter.Ordering;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.port.in.QueryNovelDetailByIdUseCase;
import com.web.novel.novel.port.in.QueryNovelDetailByIdUseCase.Result;
import com.web.novel.novel.port.out.NovelLoadPort;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NovelQueryServiceTests {

    private final NovelId novelId = new NovelId(1L);
    private final int pageSize = 10;

    private final Novel novel = Novel.initNovelWithId(
        novelId,
        new MetaInfo("test-title", "test-1234"),
        new Genre(new GenreId(1L), "로맨스"),
        SerialInfo.create("WEEKLY","금"),
        new Synopsis("test-synopsis"),
        new AuthorInfo("test-nick-name"),
        new ChapterPriceInfo(200, Policy.PAID)
    );

    @Mock
    private NovelLoadPort novelLoadPort;

    @Mock
    private ChapterLoadPort chapterLoadPort;

    private QueryNovelDetailByIdUseCase sut;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        sut = new NovelQueryDetailByIdService(novelLoadPort, chapterLoadPort);
    }

    @Test
    void 소설_상세조회_WITH_최근_업로드된_챕터_10() {

        final var chapters = getChapterFixtures(2);

        final QueryNovelDetailByIdUseCase.Query queryFixture
            = new QueryNovelDetailByIdUseCase.Query(novelId, pageSize);

        when(novelLoadPort.getById(novelId.getValue()))
            .thenReturn(novel);

        when(chapterLoadPort.getChapterOrderByUploadedAtDESCWithPage(novelId, 10))
            .thenReturn(chapters);

        Result result = sut.getNovelDetailById(queryFixture);

        assertThat(result.getChapters().size()).isEqualTo(2);
        assertThat(result.getSynopsis()).isEqualTo(novel.getSynopsis());

        verify(novelLoadPort, times(1)).getById(eq(novelId.getValue()));
        verify(chapterLoadPort, times(1)).getChapterOrderByUploadedAtDESCWithPage(eq(novelId), eq(pageSize));
    }


    private List<Chapter> getChapterFixtures(int cnt) {
        var chapters = new ArrayList<Chapter>();

        for(int i = 0; i < cnt; i++) {
            chapters.add(createChapterFixture(i));
        }

        return chapters;
    }

    private Chapter createChapterFixture(int i) {
        final String uploadDTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        return new Chapter(
            new ChapterId((long) i),
            new ChapterTitle("chapter title"),
            new ChapterContent("Cotent Test"),
            new AuthorTalk("Author Talk"),
            new Novel.NovelId((long) i),
            new Ordering(i),
            uploadDTime
        );
    }
}