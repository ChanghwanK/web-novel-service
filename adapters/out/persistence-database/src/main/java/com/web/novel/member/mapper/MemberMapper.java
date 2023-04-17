package com.web.novel.member.mapper;

import com.web.novel.annotations.Mapper;
import com.web.novel.member.Email;
import com.web.novel.member.Member;
import com.web.novel.member.Member.MemberId;
import com.web.novel.member.NickName;
import com.web.novel.member.PointBalance;
import com.web.novel.member.entity.MemberJpaEntity;

@Mapper
public class MemberMapper {

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
            member.getMemberId() == null ? null : member.getMemberId().getValue(),
            member.getEmail().getValue(),
            member.getNickName().getValue());
    }

    public Member mapToDomain(MemberJpaEntity memberJapEntity) {
        return Member.initMemberWithId(
            new MemberId(memberJapEntity.getId()),
            new Email(memberJapEntity.getEmail()),
            new NickName(memberJapEntity.getNickName()),
            new PointBalance(memberJapEntity.getPointBalance()));
    }
}
