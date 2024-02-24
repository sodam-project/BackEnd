package ssodamproject.server.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/ip")
    public void getClientIp(HttpServletRequest request) {
        String ip = userService.getClientIP(request);
        System.out.println("ip: " + ip);
    }
}
