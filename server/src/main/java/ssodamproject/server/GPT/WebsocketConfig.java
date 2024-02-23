package ssodamproject.server.GPT;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ssodamproject.server.GPT.Service.StreamCompletionHandler;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer {

    // StreamCompletionHandler 객체를 주입받는 생성자
    private final StreamCompletionHandler streamCompletionHandler;

    // WebSocketHandlerConfigurer 인터페이스의 메서드를 구현
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // "/chat/stream" 엔드포인트에 streamCompletionHandler를 핸들러로 등록
        // 모든 오리진을 허용
        registry.addHandler(streamCompletionHandler, "/chat/stream").setAllowedOrigins("*");
    }
}
