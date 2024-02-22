package ssodamproject.server.common.exception;

import lombok.Getter;
import ssodamproject.server.common.enumSet.ErrorType;

@Getter
// RESTful API에서 발생하는 예외를 나타낸다
public class RestApiException extends RuntimeException {
    private final ErrorType errorType;

    public RestApiException(ErrorType errorType) {
        this.errorType = errorType;
    }
}
