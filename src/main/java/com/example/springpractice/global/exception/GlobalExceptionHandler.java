package com.example.springpractice.global.exception;

import com.example.springpractice.global.exception.error.ErrorCode;
import com.example.springpractice.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice가 뭐지?? -> @ControllerAdvice + @ResponseBody 조합
// 왜 여기서 다 잡지?? -> 서비스 코드 안에서 응답을 직접 만들 필요 없이 throw만 하면 되니까
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler는 왜 쓰지?? -> 이 타입의 예외가 오면 이 메서드를 실행하라는 표시
    // httpStatus를 어떻게 꺼내지? -> errorCode 안에 httpStatus 필드가 있으니까
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode));
    }

    // Exception을 여기서 잡는 이유-> Exception은 모든 예외의 최상위라서 예상 못한 에러를 전부 잡는다
    // log.error()-> 클라이언트에는 500만 주고 서버 로그에 원인을 남겨두려고
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("예상치 못한 에러 발생", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
