package com.web.novel.member.service;

import com.web.novel.member.Email;
import com.web.novel.member.Member;
import com.web.novel.member.NickName;
import com.web.novel.member.port.in.MemberRegisterUseCase;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.MemberSavePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberRegister implements MemberRegisterUseCase {

    private final MemberSavePort memberPersistencePort;
    private final MemberLoadPort memberLoadPort;

    public MemberRegister(
            final MemberSavePort memberPersistencePort,
            final MemberLoadPort memberLoadPort) {
        this.memberPersistencePort = memberPersistencePort;
        this.memberLoadPort = memberLoadPort;
    }

    @Override
    public void command(final Command command) {
        Email email = command.getEmail();
        NickName nickName = command.getNickName();

        memberLoadPort.checkDuplicatedEmail(email);
        memberLoadPort.checkDuplicatedNickName(nickName);

        memberPersistencePort.registerMember(Member.initMember(email, nickName));
    }
}
