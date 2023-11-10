package vn.sparkminds.sendMessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BodyChannelChat {
    private String chatId;
    private String textMessage;
}
