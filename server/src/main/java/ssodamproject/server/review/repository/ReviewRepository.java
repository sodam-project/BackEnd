package ssodamproject.server.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssodamproject.server.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {



}
