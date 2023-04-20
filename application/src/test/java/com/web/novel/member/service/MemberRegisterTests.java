package com.web.novel.member.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.web.novel.exception.DuplicatedEmailException;
import com.web.novel.exception.DuplicatedNickNameException;
import com.web.novel.member.Email;
import com.web.novel.member.NickName;
import com.web.novel.member.port.in.MemberRegisterUseCase;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.MemberSavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRegisterTests {
    private final String EMAIL_VALUE_FIXTURE = "dev-test@gmail.com";
    private final String NICK_NAME_VALUE_FIXTURE = "Dev";

    private final MemberRegisterUseCase.Command commandFixture = new MemberRegisterUseCase.Command(
        new Email(EMAIL_VALUE_FIXTURE),
        new NickName(NICK_NAME_VALUE_FIXTURE)
    );

    @Mock
    private MemberSavePort memberSavePort;

    @Mock
    private MemberLoadPort memberLoadPort;

    private MemberRegisterUseCase sut;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        sut = new MemberRegister(memberSavePort, memberLoadPort);
    }

    @Test
    void 신규_멤버_등록() {
        sut.command(commandFixture);

        verify(memberLoadPort, times(1)).checkDuplicatedEmail(any());
        verify(memberLoadPort, times(1)).checkDuplicatedNickName(any());
        verify(memberSavePort, times(1)).registerMember(any());
    }

    @Test
    void 이미_등록_이메일_경우_예외발생() {
        doThrow(new DuplicatedEmailException(EMAIL_VALUE_FIXTURE))
            .when(memberLoadPort)
            .checkDuplicatedEmail(any());

        assertThrows(
            DuplicatedEmailException.class,
            () -> sut.command(commandFixture)
        );
    }

    @Test
    void 이미_등록_닉네임인_경우_예외발생() {
        doThrow(new DuplicatedNickNameException(NICK_NAME_VALUE_FIXTURE))
            .when(memberLoadPort)
            .checkDuplicatedNickName(any());

        assertThrows(
            DuplicatedNickNameException.class,
            () -> sut.command(commandFixture)
        );
    }
  
}