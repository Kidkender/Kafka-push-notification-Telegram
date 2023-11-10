package vn.sparkminds.sendMessage.services;

public interface TelegramService {
    void sendMessage(String channel, String message);
}
