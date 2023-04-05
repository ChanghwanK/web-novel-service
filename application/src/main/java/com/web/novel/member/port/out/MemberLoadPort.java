package com.web.novel.member.port.out;

import com.web.novel.member.Email;
import com.web.novel.member.NickName;

public interface MemberLoadPort {
    void checkDuplicatedEmail(Email email);
    void checkDuplicatedNickName(NickName nickName);
}
