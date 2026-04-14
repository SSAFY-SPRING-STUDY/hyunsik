package com.example.springpractice.domain.post.service;

import org.springframework.stereotype.Service;

@Service  // Spring이 이 클래스를 서비스로 관리
public class HelloService {

    // 인사 메시지 반환
    public String hi() {
        return "반가워요 스프링 잘해보자고요";
    }
}