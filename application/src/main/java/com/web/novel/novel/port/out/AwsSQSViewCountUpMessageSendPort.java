package com.web.novel.novel.port.out;

import com.web.novel.novel.vo.ViewCountUpMessage;

public interface AwsSQSViewCountUpMessageSendPort {
    void sendViewCountUpMessageToSQS(ViewCountUpMessage viewCountUpMessage);
}
