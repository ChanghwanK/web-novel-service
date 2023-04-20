package com.web.novel.point.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.novel.exception.DuplicatedPointNumberException;
import com.web.novel.member.Email;
import com.web.novel.member.Member;
import com.web.novel.member.Member.MemberId;
import com.web.novel.member.NickName;
import com.web.novel.member.PointBalance;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.UpdateMemberPointBalancePort;
import com.web.novel.point.ChargeAmount;
import com.web.novel.point.PayMethod;
import com.web.novel.point.Point;
import com.web.novel.point.PointNumber;
import com.web.novel.point.port.in.PointChargeUseCase;
import com.web.novel.point.port.out.PointCacheCheckPort;
import com.web.novel.point.port.out.PointSavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointChargeServiceTests {

    private final MemberId memberIdFixture = new MemberId(1L);
    private final PayMethod payMethodFixture = PayMethod.KAKAO_PAY;
    private final ChargeAmount chargeAmountFixture = new ChargeAmount(10_000);

    private final PointChargeUseCase.Command commandFixture = new PointChargeUseCase.Command(
        memberIdFixture, payMethodFixture, chargeAmountFixture
    );

    @Mock
    private MemberLoadPort memberLoadPort;

    @Mock
    private PointCacheCheckPort pointCacheCheckPort;

    @Mock
    private UpdateMemberPointBalancePort updateMemberPointBalancePort;

    @Mock
    private PointSavePort pointSavePort;

    private PointChargeUseCase sut;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        sut = new PointChargeService(
            memberLoadPort,
            pointCacheCheckPort,
            updateMemberPointBalancePort,
            pointSavePort);
    }

    @Test
    void 포인트_충전을_테스트() {
        // given
        final Member memberFixture = Member.newInstanceWithId(
            memberIdFixture,
            new Email("test"),
            new NickName("test-nick-name"),
            new PointBalance(0)
        );

        when(memberLoadPort.getById(anyLong()))
            .thenReturn(memberFixture);

        doNothing()
            .when(pointCacheCheckPort)
            .checkDuplication(any());

        // when
        sut.command(commandFixture);

        // then
        verify(updateMemberPointBalancePort, times(1))
            .updateBalance(new MemberId(1L).getValue(),  10_000);

        verify(pointCacheCheckPort, times(1)).setPointNumber(any(PointNumber.class));
        verify(pointSavePort, times(1)).save(any(Point.class));
    }

    @Test
    void 이미_포인트_번호가_존재하는_경우_예외테스트() {
        // given
        final Point point = Point.createFromCommand(
            new MemberId(1L),
            new ChargeAmount(10_000),
            PayMethod.KAKAO_PAY
        ) ;

        final var pointNumber = point.createPointNumber();

        // when
        doThrow(new DuplicatedPointNumberException(pointNumber.getValue()))
            .when(pointCacheCheckPort)
            .checkDuplication(pointNumber);

        // then
        assertThrows(
            DuplicatedPointNumberException.class,
            () -> sut.command(commandFixture)
        );

        verify(pointSavePort, never()).save(any(Point.class));
    }
}