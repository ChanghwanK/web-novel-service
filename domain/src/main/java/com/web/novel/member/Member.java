package com.web.novel.member;

import com.web.novel.point.ChargeAmount;
import lombok.Value;


public class Member {

    private final MemberId memberId;
    private final Email email;
    private final NickName nickName;
    private final PointBalance remainPoint;

    /**
     * TODO -> 정팩매로 개선하여 코드 의도를 조금더 들어내기
     */
    public Member(
            final MemberId memberId,
            final Email email,
            final NickName nickName,
            final PointBalance pointBalance) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
        this.remainPoint = pointBalance;
    }

    public MemberId getMemberId() { return memberId; }

    public Email getEmail() { return email;}

    public NickName getNickName() { return nickName; }

    public PointBalance getRemainPoint() { return remainPoint; }

    public static Member initMemberWithId(
            final MemberId memberId,
            final Email email,
            final NickName nickName,
            final PointBalance pointBalance) {
        return new Member(memberId, email, nickName, pointBalance);
    }

    public static Member initMember(final Email email, final NickName nickName) {
        return new Member(null, email, nickName, PointBalance.ZERO);
    }

    public PointBalance pointCharge(ChargeAmount chargeAmount) {
        return remainPoint.charge(chargeAmount.getValue());
    }

    @Value
    public static class MemberId {
        Long value;
    }
}
