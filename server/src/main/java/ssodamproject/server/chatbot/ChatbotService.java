package ssodamproject.server.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ssodamproject.server.chatbot.dto.ChatbotDto;
import ssodamproject.server.chatbot.dto.CreateChatbotReviewDto;
import ssodamproject.server.common.api.*;
import ssodamproject.server.heart.Heart;
import ssodamproject.server.heart.HeartRepository;
import ssodamproject.server.review.entity.Review;
import ssodamproject.server.review.repository.ReviewRepository;
import ssodamproject.server.user.entity.User;
import ssodamproject.server.user.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatbotService {

    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final ChatbotRepository chatbotRepository;
    private final ReviewRepository reviewRepository;


    public ApiResponseDto<SuccessResponse> createHeart(String clientIp, Long chatbotId) {
        Optional<User> userOptional = userRepository.findByUserIp(clientIp);
        Chatbot chatbot = chatbotRepository.findByChatbotId(chatbotId)
                .orElseThrow(
                        () -> new RestApiException(ErrorType.NOT_FOUND_CHATBOT)
                );

        if (userOptional.isPresent()) { // 이미 있는 유저인 경우
            User user = userOptional.get();
            List<Heart> heartList = user.getHearts();

            for (Heart h : heartList) {
                if (h.getChatbot() == chatbot) { // 이미 해당 챗봇에 하트를 누른 경우 -> 좋아요 삭제
                    heartRepository.delete(h);
                    return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "좋아요가 삭제되었습니다."), null);
                }
            }

            // 하트에 좋아요를 누르지 않은 경우 -> 좋아요 생성
            Heart heart = Heart.builder()
                    .user(user)
                    .chatbot(chatbot).build();

            heartRepository.save(heart);

            return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "좋아요가 저장되었습니다."), null);
        }
        else { // 접속한 적 없는 유저인 경우
            User user = User.builder()
                    .userIp(clientIp).build();
            userRepository.save(user);

            Heart heart = Heart.builder()
                    .chatbot(chatbot)
                    .user(user).build();
            heartRepository.save(heart);

            return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "좋아요가 저장되었습니다."), null);
        }
    }

    public ApiResponseDto<List<ChatbotDto>> readChatbotList() {
        List<Chatbot> chatbotList = chatbotRepository.findAll();

        if (chatbotList.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_CHATBOT_LIST);
        }

        Collections.sort(chatbotList, Comparator.comparing(chatbot -> chatbot.getHearts().size(), Comparator.reverseOrder())); // 내림차순 정렬

        List<ChatbotDto> chatbotDtoList = new ArrayList<>();

        int rank = 1;
        int nowHeart = chatbotList.get(0).getHearts().size();

        for (int i = 0; i < chatbotList.size(); i++) {
            Chatbot chatbot = chatbotList.get(i);
            int hearts = chatbot.getHearts().size();

            if (hearts < nowHeart)
                rank++;

            ChatbotDto chatbotDto = ChatbotDto.builder()
                    .chatbotId(chatbot.getChatbotId())
                    .chatbotName(chatbot.getChatbotName())
                    .chatbotLike(hearts)
                    .chatbotRanking(rank)
                    .build();
            chatbotDtoList.add(chatbotDto);

            nowHeart = hearts;
        }

        return ResponseUtils.ok(chatbotDtoList, null);
    }

    public ApiResponseDto<SuccessResponse> createReview(Long chatbotId, CreateChatbotReviewDto createChatbotReviewDto) {
        Chatbot chatbot = chatbotRepository.findByChatbotId(chatbotId)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_CHATBOT));

        Review review = Review.builder()
                .chatbot(chatbot)
                .reviewContent(createChatbotReviewDto.getReviewContent()).build();

        reviewRepository.save(review);

        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "리뷰가 등록되었습니다."), null);
    }
}
