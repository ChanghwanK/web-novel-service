package com.web.novel;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.DuplicatedEmailException;
import com.web.novel.exception.DuplicatedNickNameException;
import com.web.novel.member.Email;
import com.web.novel.member.Member;
import com.web.novel.member.NickName;
import com.web.novel.member.mapper.MemberMapper;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.MemberPersistencePort;
import com.web.novel.member.repository.MemberRepository;

@PersistenceAdapter
public class MemberJpaPersistenceAdapter implements MemberPersistencePort, MemberLoadPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberJpaPersistenceAdapter(
            final MemberRepository memberRepository,
            final MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }


    @Override
    public void registerMember(Member member) {
        memberRepository.save(memberMapper.mapToJpaEntity(member));
    }


    @Override
    public void checkDuplicatedEmail(Email email) {
        if(memberRepository.existsByEmail(email.getValue()))
            throw new DuplicatedEmailException(email.getValue());
    }

    @Override
    public void checkDuplicatedNickName(NickName nickName) {
        if(memberRepository.existsByNickName(nickName.getValue()))
            throw new DuplicatedNickNameException(nickName.getValue());
    }
}
