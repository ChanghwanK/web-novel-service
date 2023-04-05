package com.web.novel.member;

import lombok.Value;


public class Member {
    MemberId memberId;
    Email email;
    NickName nickName;

    public Member(Email email, NickName nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public MemberId getMemberId() { return memberId; }

    public Email getEmail() { return email;}

    public NickName getNickName() { return nickName; }

    @Value
    public static class MemberId {
        Long value;
    }
}
