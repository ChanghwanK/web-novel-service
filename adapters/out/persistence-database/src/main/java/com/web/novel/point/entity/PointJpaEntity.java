package com.web.novel.point.entity;

import com.web.novel.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
    name = "points",
    indexes = {
        @Index(name = "uq_point_number", columnList = "point_number", unique = true)
    }
)
public class PointJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meber_id", nullable = false)
    private Long memberId;

    @Column(name = "charge_amount", nullable = false)
    private int chargeAmount;

    @Column(name = "pay_method", nullable = false)
    private String payMethod;

    @Column(name = "point_number", nullable = false)
    private String pointNumber;

    protected PointJpaEntity() {};

    public PointJpaEntity(
            final Long memberId,
            final int chargeAmount,
            final String payMethod,
            final String pointNumber) {
        this.memberId = memberId;
        this.chargeAmount = chargeAmount;
        this.payMethod = payMethod;
        this.pointNumber = pointNumber;
    }

    public static PointJpaEntity initForSave(
            final Long memberId,
            final int chargeAmount,
            final String payMethod,
            final String pointNumber) {
        return new PointJpaEntity(memberId, chargeAmount, payMethod, pointNumber);
    }
}
