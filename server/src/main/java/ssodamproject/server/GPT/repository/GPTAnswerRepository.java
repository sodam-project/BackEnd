package ssodamproject.server.GPT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssodamproject.server.GPT.entity.GPTAnswer;

public interface GPTAnswerRepository extends JpaRepository<GPTAnswer, Integer> {
}
