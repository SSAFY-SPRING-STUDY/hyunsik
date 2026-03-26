package com.example.springpractice.controller;

import com.example.springpractice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 웹 요청을 처리하는 컨트롤러
public class HelloController {
    @Autowired // 생성자 자동 넣어줌
    private HelloService helloService;

    // 생성자 직접 작성 - Spring이 HelloService를 자동으로 넣어줌
//    public HelloController(HelloService helloService) {
//        this.helloService = helloService;
//    }

    @GetMapping("/hello")
    public String hello() {
        String str = helloService.hi();  // 서비스에서 메시지 받아옴
        return str;                      // 브라우저에 출력
    }
}