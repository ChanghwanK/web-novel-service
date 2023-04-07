package com.web.novel.member;

import lombok.Value;


public class Member {
    MemberId memberId;
    Email email;
    NickName nickName;

    public Member(
            final MemberId memberId,
            final Email email,
            final NickName nickName) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
    }

    public MemberId getMemberId() { return memberId; }

    public Email getEmail() { return email;}

    public NickName getNickName() { return nickName; }

    public static Member initMemberWithId(
            final MemberId memberId,
            final Email email,
            final NickName nickName) {
        return new Member(memberId, email, nickName);
    }

    public static Member initMember(final Email email, final NickName nickName) {
        return new Member(null, email, nickName);
    }


    @Value
    public static class MemberId {
        Long value;
    }
}
