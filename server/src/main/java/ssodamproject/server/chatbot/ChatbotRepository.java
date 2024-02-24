package ssodamproject.server.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {
    Optional<Chatbot> findByChatbotId(Long chatbotId);
}
