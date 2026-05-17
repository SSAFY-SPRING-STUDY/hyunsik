package com.example.springpractice.global.response;

import com.example.springpractice.global.exception.error.ErrorCode;

// record는 생성자, getter, equals, hashCode, toString을 자동으로 만들어준다
// 근데 왜 T data 처럼 제네릭을 쓰지?? -> data에 들어오는 타입이 뭔지 모르니까
public record ApiResponse<T>(String message, T data) {

    // 정적 메서드에 왜 T를 또 선언하는가 -> static은 인스턴스 없이 호출되니까 record의 T를 못 쓴다
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(message, null);
    }

    // ErrorCode enum 안에 message 필드가 있으니까
    public static ApiResponse<Void> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode, T data) {
        return new ApiResponse<>(errorCode.getMessage(), data);
    }
}
