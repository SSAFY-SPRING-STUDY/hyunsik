package com.example.springpractice.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

// enum을 왜 쓰지?? -> 에러 종류가 정해진 목록이라서 오타나 이상한 값이 들어오는 걸 막을 수 있다(사실잘모르겠음)
// @RequiredArgsConstructor lombok final 필드를 받는 생성자를 자동으로 만듬
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // HTTP 상태 코드와 메시지를 한 쌍으로 묶어서 여기서만 관리한다
    // 여기서 안 하면?? -> 서비스 코드 곳곳에 HttpStatus가 흩어져서 나중에 찾기 힘들다
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 파라미터입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "해당 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INVALID_USERNAME(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 정보가 유효하지 않습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
