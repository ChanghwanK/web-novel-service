package com.web.novel.member.port.in;

import com.web.novel.member.Email;
import com.web.novel.member.NickName;
import lombok.Value;

public interface MemberRegisterUseCase {

    void command(Command command);

    @Value
    class Command {
        Email email;
        NickName nickName;
    }
}
