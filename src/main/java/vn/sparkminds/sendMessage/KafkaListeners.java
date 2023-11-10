package vn.sparkminds.sendMessage;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.sparkminds.sendMessage.services.TelegramService;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final TelegramService telegramService;

    @KafkaListener(topics = "duck1", groupId = "notification-service")
    public void test(@Payload String message) {
        log.info("Consum message: {}", message);
        telegramService.sendMessage("-1001932623919", message);
    }

    @KafkaListener(topics = "duck1", groupId = "groupId_2")
    void listener_2(ConsumerRecord<String, String> record) {
        // System.out.println("Listener 2 received " + data + " ...!!");
        String message = String.format(
                "Received Record in group Id 2 - Key: %s, Value: %s, Offset: %d, Partition: %d, Timestamp: %d",
                record.key(), record.value(), record.offset(), record.partition(),
                record.timestamp());

        System.out.println(message);
    }

    @KafkaListener(topics = "duck1", groupId = "groupId_1")
    public void listener_currentOffset(ConsumerRecord<String, String> record) {
        long offset = record.offset();
        System.out.println("Current offset of groupId_1: " + offset);
    }
}
