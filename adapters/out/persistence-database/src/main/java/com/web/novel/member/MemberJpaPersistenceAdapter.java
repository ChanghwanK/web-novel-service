package com.web.novel.member;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.DuplicatedEmailException;
import com.web.novel.exception.DuplicatedNickNameException;
import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.exception.NotExistsEmailException;
import com.web.novel.member.mapper.MemberMapper;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.MemberSavePort;
import com.web.novel.member.port.out.UpdateMemberPointBalancePort;
import com.web.novel.member.repository.MemberRepository;
import com.web.novel.novel.AuthorInfo;

@PersistenceAdapter
public class MemberJpaPersistenceAdapter implements MemberSavePort, MemberLoadPort,
    UpdateMemberPointBalancePort {

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

    @Override
    public void existsAuthorInfo(AuthorInfo authorInfo) {
        var email = authorInfo.getEmail();
        if(! memberRepository.existsByEmail(authorInfo.getEmail()))
            throw new NotExistsEmailException(email);
    }

    @Override
    public Member getByEmail(String authorEmail) {
        return memberRepository.findByEmail(authorEmail)
            .map(memberMapper::mapToDomain)
            .orElseThrow(() -> new NotExistsEmailException(authorEmail));
    }

    @Override
    public Member getById(Long memberId) {
        return memberRepository.findById(memberId)
            .map(memberMapper::mapToDomain)
            .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Override
    public void updateBalance(Long memberId, Integer balance) {
        memberRepository.findById(memberId)
            .map(memberJpaEntity -> memberJpaEntity.updateBalance(balance))
            .map(memberRepository::save);
    }
}
