package com.web.novel.member.mapper;

import com.web.novel.annotations.Mapper;
import com.web.novel.member.Member;
import com.web.novel.member.entity.MemberJpaEntity;

@Mapper
public class MemberMapper {

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
            member.getMemberId() == null ? null : member.getMemberId().getValue(),
            member.getEmail().getValue(),
            member.getNickName().getValue());
    }
}
