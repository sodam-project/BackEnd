package ssodamproject.server.GPT.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ssodamproject.server.GPT.dto.GPTCompletionChatRequest;
import ssodamproject.server.GPT.dto.GPTCompletionRequest;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class StreamCompletionHandler extends TextWebSocketHandler {

    // 세션 ID를 키로 사용하여 WebSocket 세션을 관리하는 맵
    private final HashMap<String, WebSocketSession> sessionHashMap;

    // JSON 직렬화 및 역직렬화를 위한 ObjectMapper 객체
    private final ObjectMapper objectMapper = new ObjectMapper();

    // OpenAI 서비스를 사용하기 위한 서비스 객체
    private final OpenAiService openAiService;

    // 클라이언트가 접속할 때 호출되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 세션을 세션 맵에 추가
        sessionHashMap.put(session.getId(), session);
        // 접속한 클라이언트의 세션 ID를 로그에 기록
        log.info("현재 접근한 유저 : {}", session.getId());
    }

    // 클라이언트가 접속을 해제할 때 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 세션 맵에서 세션 제거
        sessionHashMap.remove(session.getId());
        // 해제된 클라이언트의 세션 ID를 로그에 기록
        log.info("연결해제 한 유저 : {}", session.getId());
    }

    // 클라이언트로부터 텍스트 메시지를 수신할 때 호출되는 메서드
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 받은 메시지를 GPTCompletionRequest 객체로 매핑
        GPTCompletionChatRequest completionChatRequest = objectMapper.readValue(message.getPayload(), GPTCompletionChatRequest.class);

        // 세션 맵의 모든 키에 대해 스트림 완성 메서드 호출
        sessionHashMap.keySet().forEach(key -> {
            streamCompletion(key, completionChatRequest);
        });
    }

    // 클라이언트에게 메시지를 전송하는 메서드
    private void streamCompletion(String key, GPTCompletionChatRequest completionChatRequest) {
        // OpenAI 서비스를 통해 스트림 완성 요청하고 결과를 클라이언트에게 전송
        openAiService.streamChatCompletion(GPTCompletionChatRequest.of(completionChatRequest))
                .blockingForEach(completion -> {
                    // 완성된 결과를 해당 클라이언트에게 전송
                    sessionHashMap.get(key).sendMessage(new TextMessage(objectMapper.writeValueAsString(completion)));
                });
    }

}