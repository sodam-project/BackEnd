package ssodamproject.server.common.enumSet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ErrorType {
    NOT_FOUND(400, "해당 정보가 존재하지 않습니다." ),
    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    NOT_FOUND_CHATBOT(400, "챗봇 ID에 해당하는 챗봇이 존재하지 않습니다."),
    NOT_FOUND_CHATBOT_LIST(404, "챗봇이 존재하지 않습니다.");


    private int code;
    private String message;

    //  Error Type 생성자 생성
    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
