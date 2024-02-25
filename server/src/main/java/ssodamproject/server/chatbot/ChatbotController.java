package ssodamproject.server.chatbot;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssodamproject.server.chatbot.dto.*;
import ssodamproject.server.common.api.ApiResponseDto;
import ssodamproject.server.common.api.SuccessResponse;
import ssodamproject.server.user.service.UserService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatbotController {
    private final ChatbotService chatbotService;
    private final UserService userService;

    @GetMapping("")
    public ApiResponseDto<List<ChatbotDto>> readChatbotList() {
        return chatbotService.readChatbotList();
    }

    @PostMapping("/{chatbotId}/like")
    public ApiResponseDto<SuccessResponse> createHeart(HttpServletRequest request, @PathVariable("chatbotId") Long chatbotId) {
        String clientIp = userService.getClientIP(request);
        return chatbotService.createHeart(clientIp, chatbotId);
    }

    @GetMapping("{chatbotId}/like")
    public ApiResponseDto<ChatbotLikeDto> readChatbotLiked(HttpServletRequest request, @PathVariable("chatbotId") Long chatbotId) {
        String clientIp = userService.getClientIP(request);
        return chatbotService.readChatbotLiked(clientIp, chatbotId);
    }

    @PostMapping("/{chatbotId}/review")
    public ApiResponseDto<SuccessResponse> createReview(@PathVariable("chatbotId") Long chatbotId, @RequestBody CreateChatbotReviewDto createChatbotReviewDto) {
        return chatbotService.createReview(chatbotId, createChatbotReviewDto);
    }

    @GetMapping("{chatbotId}/review")
    public ApiResponseDto<ChatbotReviewDto> readReviewList(@PathVariable("chatbotId") Long chatbotId) {
        return chatbotService.readReviewList(chatbotId);
    }
}
