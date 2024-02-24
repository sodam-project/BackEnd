package ssodamproject.server.chatbot.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatbotLikeDto {
    private Boolean isLiked;
}
