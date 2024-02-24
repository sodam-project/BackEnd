package ssodamproject.server.chatbot.dto;

import lombok.*;

@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatbotDto {
    private Long chatbotId;
    private String chatbotName;
    private Integer chatbotLike;
    private Integer chatbotRanking;
}
