package com.web.novel.point;

import com.web.novel.member.Member.MemberId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * 충전과, 사용 내역을 관리
 * - 충전 ID
 * - 충전 금액
 */
@EqualsAndHashCode(of = {"memberId", "chargeAmount", "payMethod"})
public class Point {

    private static final DateTimeFormatter DATE_FORMAT
        = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private PointId pointId;
    private final MemberId memberId;
    private final ChargeAmount chargeAmount;
    private final PayMethod payMethod;
    private PointNumber pointNumber;

    private Point(
            final MemberId memberId,
            final ChargeAmount chargeAmount,
            final PayMethod payMethod) {
        this.memberId = memberId;
        this.chargeAmount = chargeAmount;
        this.payMethod = payMethod;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public ChargeAmount getChargeAmount() {
        return chargeAmount;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public PointNumber getPointNumber() {
        return pointNumber;
    }

    public static Point createFromCommand(
            final MemberId memberId,
            final ChargeAmount chargeAmount,
            final PayMethod payMethod) {

        return new Point(memberId, chargeAmount, payMethod);
    }

    public PointNumber createPointNumber() {
        var time = LocalDateTime.now().format(DATE_FORMAT);
        this.pointNumber = new PointNumber(this.hashCode() + time);
        return pointNumber;
    }

    @Value
    public static class PointId {
        Long value;
    }
}
