package com.web.novel.member.port.out;

import com.web.novel.member.Member;

public interface MemberPersistencePort {
    void registerMember(Member member);
}
