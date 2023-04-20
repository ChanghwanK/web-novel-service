package com.web.novel.novel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import com.web.novel.novel.AuthorInfo.AuthorId;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.ChapterPriceInfo.Policy;
import com.web.novel.novel.Genre.GenreId;
import com.web.novel.novel.MetaInfo;
import com.web.novel.novel.SerialInfo;
import com.web.novel.novel.Synopsis;
import com.web.novel.novel.Tag;
import com.web.novel.novel.port.in.NovelRegisterUseCase;
import com.web.novel.novel.port.out.NovelRegisterPort;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NovelRegisterServiceTests {

    private final String NOVEL_TITLE = "Test Title 입니다.";
    private final String COVER_IMAGE_URL = "https://test-1234";

    private final NovelRegisterUseCase.Command commandFixture = new NovelRegisterUseCase.Command(
        new MetaInfo(NOVEL_TITLE, COVER_IMAGE_URL),
        new ChapterPriceInfo(200, Policy.PAID),
        SerialInfo.create("WEEKLY", "금"),
        new GenreId(1L),
        new Synopsis("Test 소개글 입니다."),
        List.of(new Tag("로맨스")),
        new AuthorId(1L)
    );

    @Mock
    private NovelRegisterPort novelRegisterPort;

    private NovelRegisterUseCase sut;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        sut = new NovelRegisterService(novelRegisterPort);
    }

    @Test
    void 신규_소설_등록() {
        sut.command(commandFixture);
        Mockito.verify(novelRegisterPort, times(1)).register(any());
    }


}