package ssodamproject.server.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssodamproject.server.chatbot.Chatbot;
import ssodamproject.server.review.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByChatbot(Chatbot chatbot);


}
