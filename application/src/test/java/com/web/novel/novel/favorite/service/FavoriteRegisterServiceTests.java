package com.web.novel.novel.favorite.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.favorite.Favorite;
import com.web.novel.novel.favorite.port.FavoriteUpsertUseCase;
import com.web.novel.novel.favorite.port.out.FavoriteDeletePort;
import com.web.novel.novel.favorite.port.out.FavoriteLoadPort;
import com.web.novel.novel.favorite.port.out.FavoriteRegisterPort;
import com.web.novel.novel.port.out.NovelLoadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FavoriteRegisterServiceTests {

    private final NovelId novelIdFixture = new NovelId(1L);
    private final MemberId memberIdFixture = new MemberId(1L);

    private final FavoriteUpsertUseCase.Command commandFixture =
        new FavoriteUpsertUseCase.Command(novelIdFixture, memberIdFixture);

    @Mock
    private FavoriteRegisterPort favoriteRegisterPort;

    @Mock
    private FavoriteDeletePort favoriteDeletePort;

    @Mock
    private FavoriteLoadPort favoriteLoadPort;

    @Mock
    private NovelLoadPort novelLoadPort;

    @Mock
    private ChapterLoadPort chapterLoadPort;

    private FavoriteUpsertUseCase sut;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        sut = new FavoriteRegisterService(favoriteRegisterPort, favoriteDeletePort,
            favoriteLoadPort, novelLoadPort, chapterLoadPort);
    }

    @Test
    void 선호작_등록_테스트() {
        when(favoriteLoadPort.getByMemberIdAndNovelId(memberIdFixture, novelIdFixture))
            .thenReturn(null);

        sut.command(commandFixture);

        verify(favoriteRegisterPort, times(1)).register(any(Favorite.class));
    }

    @Test
    void 잘못된_NOVEL_ID인_경우_예외테스트() {
        var novelIdFixtureValue = novelIdFixture.getValue();

        doThrow(new NovelNotFoundException(novelIdFixtureValue))
            .when(novelLoadPort)
            .getById(novelIdFixtureValue);

        assertThrows(
            NovelNotFoundException.class,
            () -> sut.command(commandFixture)
        );

        verify(favoriteRegisterPort, never()).register(any(Favorite.class));
    }

    @Test
    void 잘못된_USER_ID인_경우_예외테스트() {
        var memberIdFixtureValue = memberIdFixture.getValue();

        doThrow(new MemberNotFoundException(memberIdFixtureValue))
            .when(favoriteRegisterPort)
            .register(any(Favorite.class));

        when(favoriteLoadPort.getByMemberIdAndNovelId(memberIdFixture, novelIdFixture))
            .thenReturn(null);

        assertThrows(
            MemberNotFoundException.class,
            () -> sut.command(commandFixture)
        );

        verify(favoriteRegisterPort, times(1)).register(any(Favorite.class));
    }
}