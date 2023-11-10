package vn.sparkminds.sendMessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyUserChat {
    private String userId;
    private String textMessage;
}
