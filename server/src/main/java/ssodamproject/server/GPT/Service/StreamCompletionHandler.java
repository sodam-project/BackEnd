package ssodamproject.server.GPT.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.service.OpenAiService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ssodamproject.server.GPT.dto.GPTCompletionChatRequest;
import ssodamproject.server.GPT.dto.GPTCompletionRequest;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamCompletionHandler extends TextWebSocketHandler {

    private final HashMap<String, WebSocketSession> sessionHashMap;
    private final ObjectMapper objectMapper;
    private final OpenAiService openAiService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionHashMap.put(session.getId(), session);
        log.info("현재 접근한 유저 : {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionHashMap.remove(session.getId());
        log.info("연결해제 한 유저 : {}", session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try {
            GPTCompletionChatRequest completionChatRequest = objectMapper.readValue(message.getPayload(), GPTCompletionChatRequest.class);
            sessionHashMap.keySet().forEach(key -> streamCompletion(key, completionChatRequest));
        } catch (IOException e) {
            log.error("Error handling text message:", e);
        }
    }

    private void streamCompletion(String key, GPTCompletionChatRequest completionChatRequest) {
        openAiService.streamChatCompletion(GPTCompletionChatRequest.of(completionChatRequest))
                .blockingForEach(completion -> {
                    try {
                        sessionHashMap.get(key).sendMessage(new TextMessage(objectMapper.writeValueAsString(completion)));
                    } catch (IOException e) {
                        log.error("Error sending message:", e);
                    }
                });
    }

}