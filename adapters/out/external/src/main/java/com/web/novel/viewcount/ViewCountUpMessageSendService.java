package com.web.novel.viewcount;

import com.web.novel.novel.port.out.AwsSQSViewCountUpMessageSendPort;
import com.web.novel.novel.vo.ViewCountUpMessage;
import org.springframework.stereotype.Component;

@Component
public class ViewCountUpMessageSendService implements AwsSQSViewCountUpMessageSendPort {

    private final ViewCountMessageSender viewCountMessageSender;

    public ViewCountUpMessageSendService(final ViewCountMessageSender viewCountMessageSender) {
        this.viewCountMessageSender = viewCountMessageSender;
    }

    @Override
    public void sendViewCountUpMessageToSQS(ViewCountUpMessage viewCountUpMessage) {
        viewCountMessageSender.sendMessage(viewCountUpMessage);
    }
}
