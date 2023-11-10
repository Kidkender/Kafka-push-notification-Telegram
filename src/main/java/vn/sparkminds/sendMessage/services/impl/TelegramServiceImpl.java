package vn.sparkminds.sendMessage.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;
import lombok.extern.slf4j.Slf4j;
import vn.sparkminds.sendMessage.services.TelegramService;

@Slf4j
@Service
public class TelegramServiceImpl implements TelegramService {

    @Value("${telegram.bot.token:}")
    private String botToken;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendMessage(String channelId, String message) {
        String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        String requestUrl = "%s?chat_id=%s&text=%s".formatted(apiUrl, channelId, message);
        log.info("URL: {}", UriEncoder.encode(requestUrl));
        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        log.info("Send message to Telegram channel id: {} successfully with response: {}",
                channelId, response.getBody());
    }
}
