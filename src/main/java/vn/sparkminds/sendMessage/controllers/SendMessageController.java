package vn.sparkminds.sendMessage.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import vn.sparkminds.sendMessage.model.BodyChannelChat;
import vn.sparkminds.sendMessage.model.BodyUserChat;
import vn.sparkminds.sendMessage.services.TelegramService;

@RestController
@RequestMapping("/api/bot")
@RequiredArgsConstructor
public class SendMessageController {
    @Value("${telegram.bot.token}")
    private String botToken;
    private TelegramService telegramService;

    @PostMapping(value = "/sendMessage/channel")
    public ResponseEntity<String> sendMessageChannelHandler(@RequestBody BodyChannelChat req) {
        String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl =
                apiUrl + "?chat_id=" + req.getChatId() + "&text=" + req.getTextMessage();

        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        return response;
    }

    @PostMapping(value = "/auto/sendmessages")
    public void autoSendMessageHandler(@RequestBody String mess) {

        telegramService.sendMessage(botToken, mess);
    }

    @PostMapping(value = "/sendMessage/user")
    public ResponseEntity<String> sendMessageUserHandler(@RequestBody BodyUserChat req) {
        String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl =
                apiUrl + "?chat_id=" + req.getUserId() + "&text=" + req.getTextMessage();

        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        return response;
    }

}
