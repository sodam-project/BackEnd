package ssodamproject.server.GPT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssodamproject.server.GPT.entity.GPTQuestion;

public interface GPTQuestionRepository extends JpaRepository<GPTQuestion, Integer> {

}
