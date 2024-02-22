package ssodamproject.server.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssodamproject.server.review.entity.Review;

public interface reviewRepository extends JpaRepository<Review, Long> {



}
