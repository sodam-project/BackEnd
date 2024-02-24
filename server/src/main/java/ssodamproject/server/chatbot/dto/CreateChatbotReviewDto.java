package ssodamproject.server.chatbot.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateChatbotReviewDto {
    private String reviewContent;
}
