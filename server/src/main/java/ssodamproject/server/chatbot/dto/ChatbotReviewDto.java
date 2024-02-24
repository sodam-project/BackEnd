package ssodamproject.server.chatbot.dto;

import lombok.*;
import ssodamproject.server.review.Dto.ReviewDto;
import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatbotReviewDto {
    private Long chatbotId;
    private String chatbotName;

    private List<ReviewDto> reviewList;
}
