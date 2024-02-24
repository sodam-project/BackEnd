package ssodamproject.server.chatbot.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatbotListDto {
    private List<ChatbotDto> chatbotDtoList;
}
