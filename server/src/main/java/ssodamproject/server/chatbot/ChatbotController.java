package ssodamproject.server.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssodamproject.server.common.api.ApiResponseDto;
import ssodamproject.server.common.api.SuccessResponse;
import ssodamproject.server.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatbotController {
    private final ChatbotService chatbotService;
    private final UserService userService;

    @PostMapping("/like/{chatbotId}")
    public ApiResponseDto<SuccessResponse> createHeart(HttpServletRequest request, @PathVariable("chatbotId") Long chatbotId) {
        String clientIp = userService.getClientIP(request);
        return chatbotService.createHeart(clientIp, chatbotId);
    }
}
