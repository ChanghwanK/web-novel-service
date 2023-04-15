package com.web.novel.member;

import lombok.Value;


public class Member {

    private MemberId memberId;
    private Email email;
    private NickName nickName;
    private Cache cache;

    /**
     * TODO -> 정팩매로 개선하여 코드 의도를 조금더 들어내기
     */
    public Member(
            final MemberId memberId,
            final Email email,
            final NickName nickName) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
        this.cache = Cache.ZERO;
    }

    public MemberId getMemberId() { return memberId; }

    public Email getEmail() { return email;}

    public NickName getNickName() { return nickName; }

    public static Member initMemberWithId(final MemberId memberId, final Email email, final NickName nickName) {
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
