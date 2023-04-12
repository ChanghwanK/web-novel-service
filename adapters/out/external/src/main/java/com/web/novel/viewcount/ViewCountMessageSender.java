package com.web.novel.viewcount;

import com.web.novel.novel.vo.ViewCountUpMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ViewCountMessageSender {

    private static final String SQS_QUEUE_NAME = "web-novel-service-view-count-sqs.fifo";
    private static final String SQS_GROUP_ID_HEADER_VALUE = "view-count-message";
    private final QueueMessagingTemplate queueMessagingTemplate;

    public ViewCountMessageSender(final QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void sendMessage(ViewCountUpMessage message) {
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, SQS_GROUP_ID_HEADER_VALUE);
            headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
            queueMessagingTemplate.convertAndSend(SQS_QUEUE_NAME, message, headers);
            log.info("[SQS enqueued topic: {}, message: {}", SQS_QUEUE_NAME, message);
        } catch (Exception e) {
            log.error("SQS failed topic: {}, message: {}", SQS_QUEUE_NAME, message);
            throw new RuntimeException(e);
        }
    }
}
