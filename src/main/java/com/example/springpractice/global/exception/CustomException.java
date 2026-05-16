package com.example.springpractice.global.exception;

import com.example.springpractice.global.exception.error.ErrorCode;
import lombok.Getter;

// RuntimeException을 상속하면 왜 좋지?? -> throws 선언 없이 어디서든 던질 수 있다
// Checked Exception이 아닌 이유?? -> 비즈니스 예외는 대부분 복구 불가능하니까 매번 처리를 강제할 필요가 없다
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        // super에 메시지를 넘기는 이유?? -> e.getMessage()로 꺼낼 수 있게 하려고
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
