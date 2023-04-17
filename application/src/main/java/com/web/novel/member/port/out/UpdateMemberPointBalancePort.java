package com.web.novel.member.port.out;

public interface UpdateMemberPointBalancePort {
    void updateBalance(Long memberId, Integer balance);
}
