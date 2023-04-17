package com.web.novel.member.entity;

import com.web.novel.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(
    name = "members",
    indexes = {
        @Index(name = "uq_member_email", columnList = "email", unique = true),
        @Index(name = "uq_member_nick_name", columnList = "nick_name", unique = true)
    }
)
public class MemberJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "point_balance", nullable = false)
    private int pointBalance;

    protected MemberJpaEntity() {}

    public MemberJpaEntity(Long id, String email, String nickName) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
    }

    public MemberJpaEntity updateBalance(final Integer balance) {
        this.pointBalance = balance;
        return this;
    }
}
