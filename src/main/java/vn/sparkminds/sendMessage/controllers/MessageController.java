package vn.sparkminds.sendMessage.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sparkminds.sendMessage.KafkaMessageRetriever;
import vn.sparkminds.sendMessage.model.MessageRequest;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaMessageRetriever kafkaMessageRetriever;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate,
            KafkaMessageRetriever kafkaMessageRetriever) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaMessageRetriever = kafkaMessageRetriever;
    }

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        kafkaTemplate.send("duck1", request.message());
    }

    @GetMapping("/{topic}/{partition}/{offset}")
    public ResponseEntity<String> getMessageAtOffset(@PathVariable String topic,
            @PathVariable int partition, @PathVariable long offset) {
        String message = kafkaMessageRetriever.getMessageAtOffset(topic, partition, offset);
        kafkaTemplate.send("duck1", "Message at offset " + offset + ": " + message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
