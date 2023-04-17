package com.web.novel.point.port.in;

import com.web.novel.member.Member.MemberId;
import com.web.novel.point.ChargeAmount;
import com.web.novel.point.PayMethod;
import lombok.Value;

public interface PointChargeUseCase {
    void command(Command command);

    @Value
    class Command {
        MemberId memberId;
        PayMethod payMethod;
        ChargeAmount chargeAmount;
    }
}
