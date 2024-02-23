package ssodamproject.server.GPT.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssodamproject.server.GPT.Service.GPTChatRestService;
import ssodamproject.server.GPT.dto.CompletionChatResponse;
import ssodamproject.server.GPT.dto.CompletionResponse;
import ssodamproject.server.GPT.dto.GPTCompletionChatRequest;
import ssodamproject.server.GPT.dto.GPTCompletionRequest;

@RestController
@RequestMapping("/api/chatgpt/rest")
@RequiredArgsConstructor
public class ChatGPTRestController {

    private final GPTChatRestService gptChatRestService;

    @PostMapping("/completion")
    public CompletionResponse completion(final @RequestBody GPTCompletionRequest gptCompletionRequest) {

        return gptChatRestService.completion(gptCompletionRequest);
    }

    @PostMapping("/completion/chat")
    public CompletionChatResponse completionChat(final @RequestBody GPTCompletionChatRequest gptCompletionChatRequest) {

        return gptChatRestService.completionChat(gptCompletionChatRequest);
    }

}
