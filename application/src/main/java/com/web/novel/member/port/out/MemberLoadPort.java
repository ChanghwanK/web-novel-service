package com.web.novel.member.port.out;

import com.web.novel.member.Email;
import com.web.novel.member.Member;
import com.web.novel.member.NickName;
import com.web.novel.novel.AuthorInfo;

public interface MemberLoadPort {
    void checkDuplicatedEmail(Email email);
    void checkDuplicatedNickName(NickName nickName);
    void existsAuthorInfo(AuthorInfo email);
    Member getByEmail(String authorEmail);
    Member getById(Long memberId);
}
